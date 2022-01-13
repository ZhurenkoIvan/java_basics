package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;

//Берет данные из столбца content таблицы page и вычленяет леммы
public class LemmaParser {
    private static final Connection connection = DBConnection.getConnection();
    private static PreparedStatement prStLemma;

    static {
        try {
            prStLemma = connection.prepareStatement("INSERT INTO lemma(lemma, frequency) VALUES(?, ?) " +
                    "ON DUPLICATE KEY UPDATE frequency = frequency + 1");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void addLemmas() throws SQLException, IOException {
        connection.createStatement().execute("DROP TABLE lemma");
        connection.createStatement().execute("CREATE TABLE lemma (" +
                "PRIMARY KEY(id), id INT NOT NULL AUTO_INCREMENT, " +
                "lemma VARCHAR(255) NOT NULL, " +
                "frequency INT NOT NULL, " +
                "UNIQUE (lemma))");

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
}
