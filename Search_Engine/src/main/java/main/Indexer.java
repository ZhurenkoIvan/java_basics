package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

public class Indexer {
    private static final Connection connection = DBConnection.getConnection();
    private static PreparedStatement prStLemma;
    private static PreparedStatement prSt_Index;

    static {
        try {
            prStLemma = connection.prepareStatement("INSERT INTO lemma(lemma, frequency) VALUES(?, ?) " +
                    "ON DUPLICATE KEY UPDATE frequency = frequency + 1");
            prSt_Index = connection.prepareStatement("INSERT INTO _index(page_id, lemma_id, _rank) VALUES(?, ?, ?) " +
                    "ON DUPLICATE KEY UPDATE _rank = _rank + (?)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public void addLemmas() throws SQLException, IOException {
        ResultSet rsPages = connection.createStatement().executeQuery("SELECT content FROM search_engine.page");
        int pathsCount = 0;
        while (rsPages.next()) {
            if (pathsCount > 30) {
                prStLemma.executeBatch();
                pathsCount = 0;
            }
            //Добавление лемм
            Document contentDoc = Jsoup.parse(rsPages.getString("content"));
            String title = contentDoc.title();
            String body = contentDoc.text();
            Lemmatizator titleLemma = new Lemmatizator(title);
            Lemmatizator bodyLemma = new Lemmatizator(body);
            HashMap<String, Integer> titleMap = titleLemma.getLemmas();
            HashMap<String, Integer> bodyMap = bodyLemma.getLemmas();
            for (String key : titleMap.keySet()) {
                prStLemma.setString(1, key);
                prStLemma.setInt(2, 1);
                prStLemma.addBatch();
            }
            for (String key : bodyMap.keySet()) {
                prStLemma.setString(1,key);
                prStLemma.setInt(2, 1);
                prStLemma.addBatch();
            }


            pathsCount++;

        }
        prStLemma.executeBatch();
    }

    public void addIndexes() throws SQLException, IOException {
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
        int pathsCount = 0;
        while (rsPages.next()) {
            if (pathsCount > 30) {
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
