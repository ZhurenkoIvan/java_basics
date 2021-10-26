import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.*;
import java.util.concurrent.RecursiveTask;

public class Site extends RecursiveTask<HashSet<String>> {
    Elements elements;

    public Site(Elements elements) {
        this.elements = elements;
    }


    @Override
    protected HashSet<String> compute() {
        HashSet<String> links = new HashSet<>();
        Collections.synchronizedSet(links);
        Vector<Site> taskList = new Vector<>();
        elements.forEach(element -> {
            String link = element.absUrl("href");
            if (link.contains("https://lenta.ru/") && !link.contains("#") && !links.contains(link)) {
                links.add(link);
                try {
                    Thread.sleep(130);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Site task = new Site(getLinks(link));
                task.fork();
                taskList.add(task);
            }
        });

        taskList.forEach(task -> {
            links.addAll(task.join());
        });


       return links;
    }

    private Elements getLinks(String url) {
        try {
            Connection con = Jsoup.connect(url);
            Connection.Response resp = con.execute();
            Document doc = null;
            if (resp.statusCode() == 200) {
                doc = con.get();
            }
            Elements elements = doc.select("a");
            return elements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
