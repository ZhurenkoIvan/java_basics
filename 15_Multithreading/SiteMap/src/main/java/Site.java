import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.RecursiveTask;

public class Site extends RecursiveTask<List<String>> {
    Elements elements;

    public Site(Elements elements) {
        this.elements = elements;
    }


    @Override
    protected List<String> compute() {
        List<String> links = new ArrayList<>();
        List<Site> taskList = new ArrayList<>();
        elements.forEach(element -> {
            String link = element.absUrl("href");
            if (link.contains("https://lenta.ru/") && !link.contains("#")) {
                links.add(link);
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
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("a");
            return elements;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
