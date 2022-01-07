package main;
import java.sql.*;

public class DBConnection {

    private static Connection connection;

    private static String dbName = "search_engine";
    private static String dbUser = "root";
    private static String dbPass = "Vitylin97";

    public static Connection getConnection() {
        if (connection == null) {
            try {
                connection = DriverManager.getConnection(
                        "jdbc:mysql://localhost:3306/" + dbName +
                                "?user=" + dbUser + "&password=" + dbPass);


                connection.createStatement().execute("CREATE TABLE IF NOT EXISTS page (" +
                                "id INT NOT NULL AUTO_INCREMENT, " +
                                "path TINYTEXT NOT NULL, " +
                                "code INT NOT NULL, " +
                                "content MEDIUMTEXT NOT NULL, " +
                                "PRIMARY KEY(id), " +
                                "UNIQUE KEY path(path(50)))");


                connection.createStatement().execute("CREATE TABLE IF NOT EXISTS field (" +
                        "PRIMARY KEY(id), id INT NOT NULL AUTO_INCREMENT, " +
                        "name VARCHAR(255) NOT NULL, " +
                        "selector VARCHAR(255) NOT NULL, " +
                        "weight FLOAT NOT NULL, " +
                        "UNIQUE (name))");
                connection.createStatement().execute("INSERT INTO field(name, selector, weight) VALUES(\"title\", \"title\", 1.0) " +
                        "ON DUPLICATE KEY UPDATE weight = 1.0");
                connection.createStatement().execute("INSERT INTO field(name, selector, weight) VALUES(\"body\", \"body\", 0.8) " +
                        "ON DUPLICATE KEY UPDATE weight = 0.8");

                connection.createStatement().execute("DROP TABLE lemma");
                connection.createStatement().execute("CREATE TABLE lemma (" +
                        "PRIMARY KEY(id), id INT NOT NULL AUTO_INCREMENT, " +
                        "lemma VARCHAR(255) NOT NULL, " +
                        "frequency INT NOT NULL, " +
                        "UNIQUE (lemma))");

                connection.createStatement().execute("DROP TABLE _index");
                connection.createStatement().execute("CREATE TABLE IF NOT EXISTS _index (" +
                        "PRIMARY KEY(id), id INT NOT NULL AUTO_INCREMENT, " +
                        "page_id INT NOT NULL, " +
                        "lemma_id INT NOT NULL, " +
                        "_rank FLOAT NOT NULL, " +
                        "UNIQUE (page_id, lemma_id))");


            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return connection;
    }
}
