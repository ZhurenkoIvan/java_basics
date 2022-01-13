package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

//Осуществляет поиск нужной страницы по получаемому в конструкторе
//запросу на основе данных таблиц _index, page и lemma
public class Searcher {
    private static final Connection connection = DBConnection.getConnection();


    public ArrayList<LinkInfo> listOfLinks(String searchRequest) throws IOException, SQLException {
        ArrayList<LinkInfo> linkInfoArrayList = new ArrayList<>();

        if (searchRequest == null) {
            throw new IOException();
        }

        //Получаю леммы и создаю запрос, чтобы получить id всех лемм, отсортированных по частоте
        Lemmatizator lemmatizator = new Lemmatizator(searchRequest);
        HashMap<String, Integer> lemmas = lemmatizator.getLemmas();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SELECT * FROM search_engine.lemma where lemma in (");
        for (String lemma : lemmas.keySet()) {
            if (!lemma.contains("купить")) {
                stringBuilder.append("\"");
                stringBuilder.append(lemma);
                stringBuilder.append("\"");
                stringBuilder.append(", ");
            }
        }
        stringBuilder.setLength(stringBuilder.length() - 2);
        stringBuilder.append(") order by frequency;");
        ResultSet rs = connection.createStatement().executeQuery(stringBuilder.toString());


        HashSet<Integer> pagesIdList = new HashSet<>();
        HashSet<Integer> pagesIdListClone = null;
        HashMap<Integer, ArrayList<Float>> relevanceMap = new HashMap<>();
        while (rs.next()) {
            //Создаю список id страниц для леммы с самой маленькой frequency
            if (pagesIdList.isEmpty()) {
                int lemma_Id = rs.getInt("id");
                ResultSet pagesRS = connection.createStatement().executeQuery("SELECT page_id FROM search_engine._index WHERE lemma_id = " + lemma_Id);
                while (pagesRS.next()) {
                    pagesIdList.add(pagesRS.getInt(1));
                }
                pagesIdListClone = pagesIdList;
            }

            //Создаю карту с ключом - page_id и значением массивом rank каждой леммы
            int lemma_Id = rs.getInt("id");

            for (int page_id : pagesIdList) {
                if (pagesIdListClone.contains(page_id)) {
                    ResultSet indexRS = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
                            .executeQuery("SELECT page_id, lemma_id, _rank FROM search_engine._index WHERE lemma_id = "
                            + lemma_Id + " and page_id = " + page_id);
                    if (isResultSetEmpty(indexRS)) {
                        pagesIdListClone.remove(page_id);
                        relevanceMap.remove(page_id);
                        continue;
                    }
                    float rank = indexRS.getFloat(3);
                    ArrayList<Float> newRankList;
                    if (relevanceMap.containsKey(page_id)) {
                        newRankList = relevanceMap.get(page_id);
                    } else {
                        newRankList = new ArrayList<>();
                    }
                    newRankList.add(rank);
                    relevanceMap.put(page_id, newRankList);
                }
            }
        }

        if (relevanceMap.isEmpty()) {
            return linkInfoArrayList;
        }

        HashMap<Integer, Float> pagesSortedByRanks = new HashMap<>();
        for (Integer page_id : relevanceMap.keySet()) {
            ArrayList<Float> intermediateArray = relevanceMap.get(page_id);
            float absRank = 0.0F;
            for (Float rank : intermediateArray) {
                absRank += rank;
            }
            pagesSortedByRanks.put(page_id, absRank);
        }
        pagesSortedByRanks = sortByValue(pagesSortedByRanks);

        float maxValue = -1F;

        for (Integer page_id : pagesSortedByRanks.keySet()) {
            if (maxValue == -1F) {
                maxValue = pagesSortedByRanks.get(page_id);
            }

            ResultSet pageInfo = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)
                    .executeQuery("SELECT * FROM search_engine.page where id = " + page_id);
            pageInfo.first();
            String title = Jsoup.parse(pageInfo.getString("content")).title();
            String path = pageInfo.getString("path");

            LinkInfo linkInfo = new LinkInfo();
            linkInfo.setTitle(title);
            linkInfo.setUri(path);
            linkInfo.setRank(pagesSortedByRanks.get(page_id) / maxValue);

            linkInfoArrayList.add(linkInfo);
        }
        return linkInfoArrayList;
    }

    private static boolean isResultSetEmpty(ResultSet resultSet) throws SQLException {
        return !resultSet.first();
    }

    private static HashMap<Integer, Float> sortByValue(HashMap<Integer, Float> hashMap) {
        HashMap<Integer, Float> sortedMap = new LinkedHashMap<>();
        hashMap.entrySet().stream()
                .sorted(Map.Entry.<Integer, Float>comparingByValue().reversed())
                .forEach(e ->sortedMap.put(e.getKey(),e.getValue()));
        return sortedMap;
    }
//    public static <K, V extends Comparable<? super V>> Map<K, V>
//    sortByValue( Map<K, V> map )
//    {
//        Map<K,V> result = new LinkedHashMap<>();
//
//
//        Stream <Entry<K,V>> st = map.entrySet().stream();
//
//        st.sorted(Comparator.comparing(e -> e.getValue()))
//                .forEach(e ->result.put(e.getKey(),e.getValue()));
//
//        return result;
//    }

}
