package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//Получает данные из таблиц lemma и page. Индексирует их и записывает в таблицу _index
public class Indexer {
    private static final Connection connection = DBConnection.getConnection();

    private static PreparedStatement prSt_Index;

    static {
        try {
            prSt_Index = connection.prepareStatement("INSERT INTO _index(page_id, lemma_id, _rank) VALUES(?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE _rank = _rank + (?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addIndexes() throws SQLException, IOException {
        connection.createStatement().execute("DROP TABLE _index");
        connection.createStatement().execute("CREATE TABLE _index (" +
                "PRIMARY KEY(id), id INT NOT NULL AUTO_INCREMENT, " +
                "page_id INT NOT NULL, " +
                "lemma_id INT NOT NULL, " +
                "_rank FLOAT NOT NULL, " +
                "UNIQUE (page_id, lemma_id))");
        ResultSet tagsList = connection.createStatement().executeQuery("SELECT selector, weight FROM search_engine.field");
        HashMap<String, Float> tagsWeight = new HashMap<>();
        while (tagsList.next()) {
            String tag = tagsList.getString(1);
            Float weight = tagsList.getFloat(2);
            tagsWeight.put(tag, weight);
        }
        ResultSet rsPages = connection.createStatement().executeQuery("SELECT * FROM search_engine.page");
        ResultSet rsLemmas = connection.createStatement().executeQuery("SELECT * FROM search_engine.lemma");
        HashMap<String, Integer> lemmaIdMap = new HashMap<>();
        while (rsLemmas.next()) {
            String lemma = rsLemmas.getString("lemma");
            int id = rsLemmas.getInt("id");
            lemmaIdMap.put(lemma, id);
        }
        int pathsCount = 1;
        while (rsPages.next()) {
            if (pathsCount > 5) {
                prSt_Index.executeBatch();
                pathsCount = 1;
            }
            Document contentDoc = Jsoup.parse(rsPages.getString("content"));
            int page_id = rsPages.getInt("id");
            String title = contentDoc.title();
            String body = contentDoc.text();
            Lemmatizator titleLemma = new Lemmatizator(title);
            Lemmatizator bodyLemma = new Lemmatizator(body);
            HashMap<String, Integer> titleMap = titleLemma.getLemmas();
            HashMap<String, Integer> bodyMap = bodyLemma.getLemmas();
            addPreparedStatement(lemmaIdMap, titleMap, tagsWeight.get("title"), page_id );
            addPreparedStatement(lemmaIdMap, bodyMap, tagsWeight.get("body"), page_id );
            pathsCount++;
        }
        prSt_Index.executeBatch();
    }

    private void addPreparedStatement(HashMap<String, Integer> lemmaIdMap, HashMap<String, Integer> tagMap, float weight, int page_id ) throws SQLException {
        for (String key : tagMap.keySet()) {
            Integer lemma_id = lemmaIdMap.get(key);
            float rank = tagMap.get(key).floatValue() * weight;
            prSt_Index.setInt(1, page_id);
            prSt_Index.setInt(2, lemma_id.intValue());
            prSt_Index.setFloat(3, rank);
            prSt_Index.setFloat(4, rank);
            prSt_Index.addBatch();
        }

    }

}
