package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveAction;

public class Searcher extends RecursiveAction {
    private static Set<String> set = new HashSet<>();
    private static Set<String> AllURLS = Collections.synchronizedSet(set);
    private final String URL;
    private String path ="/";
    private static PreparedStatement prStPages;
    private static int statementSize = 0;

    public Searcher(String url) throws SQLException {
        this.URL = url.substring(0, url.length()-1);
        String sql = "INSERT INTO page(path, code, content) VALUES(?, ?, ?)";
        prStPages = DBConnection.getConnection().prepareStatement(sql);
    }

    private Searcher (String url, String path) {
        this.URL = url;
        this.path = path;
    }

    @Override
    protected void compute() {
        try {
            Document doc = Jsoup.connect(this.URL + this.path)
                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                    .referrer("http://www.google.com")
                    .get();
            Elements jsoupURLS = doc.select("a[href]");
            for (Element element : jsoupURLS) {
                String path = element.attr("href");
                if (path.contains(".html") && (!AllURLS.contains(path))) {
                    int code = Jsoup.connect(this.URL + this.path)
                            .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
                            .referrer("http://www.google.com")
                                    .execute().statusCode();
                    prStPages.setString(1, path);
                    prStPages.setInt(2, code);
                    prStPages.setString(3, doc.html());
                    prStPages.addBatch();
                    AllURLS.add(path);
                    Searcher searcher = new Searcher(this.URL, path);
                    searcher.fork();
                    searcher.join();
                }
            }
            prStPages.executeBatch();
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }

    }

    public void showAllURLS () {
        for (String url : AllURLS) {
            System.out.println(url);
        }
        System.out.println(AllURLS.size() + " размер");
    }

    private void executePages() {

    }
}
