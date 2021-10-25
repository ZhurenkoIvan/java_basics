import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ForkJoinPool;

public class Main {
    public static void main(String[] args) {
        String url = "https://lenta.ru/";
        try {
            Document doc = Jsoup.connect(url).get();
            Elements elements = doc.select("a");
            List<String> links = new ForkJoinPool().invoke(new Site(elements));
            links.forEach(System.out::println);
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
