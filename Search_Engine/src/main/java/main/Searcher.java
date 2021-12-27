package main;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.RecursiveAction;
import java.util.concurrent.RecursiveTask;

public class Searcher extends RecursiveAction {
    private static HashSet<String> hs = new HashSet<>();
    private static Set<String> AllURLS = Collections.synchronizedSet(hs);
    private String url;
    private String path;
    private static int COUNT = 1;

    public Searcher(String url) {
        this.url = url;
    }

    private Searcher (String url, String path) {
        this.url = url;
        this.path = path;
    }

    @Override
    protected void compute() {
        try {
            Document doc = Jsoup.connect(this.url)
//                    .userAgent("Mozilla/5.0 (Windows; U; WindowsNT 5.1; en-US; rv1.8.1.6) Gecko/20070725 Firefox/2.0.0.6")
//                    .referrer("http://www.google.com")
                    .get();
            Elements jsoupURLS = doc.select("a[href]");
            HashSet<String> urls = new HashSet<>();
            jsoupURLS.forEach(url -> {
                String path = url.attr("href");
                if (!AllURLS.contains(path) && path.contains(".html")) {
                    urls.add(url.attr("href"));
                }
            });
            AllURLS.addAll(urls);
            for (String url : urls) {
                System.out.println(COUNT);
                COUNT++;
                String path = url.substring(1);
                Searcher searcher = new Searcher(this.url, path);
                searcher.fork();
                searcher.join();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void showAllURLS () {
        for (String url : AllURLS) {
            System.out.println(url);
        }
        System.out.println(AllURLS.size() + " размер");
    }
}
