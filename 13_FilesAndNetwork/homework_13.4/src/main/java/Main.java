import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.regex.MatchResult;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Stream;


public class Main {
    public static void main(String[] args) {
        try {
            Document lentaHtml = Jsoup.connect("https://lenta.ru/").get();
            Elements images = lentaHtml.select("img");
            images.forEach(image -> {
                String absLink = image.attr("abs:src");
                System.out.println(absLink);
                String regex = "[.][a-z]{3,4}";
                Pattern pattern = Pattern.compile(regex);
                Matcher matcher = pattern.matcher(absLink);
                AtomicInteger end = new AtomicInteger();
                String name;
                Stream<MatchResult> results = matcher.results();
                results.map(result -> result.end())
                    .forEach(result -> end.set(result));
                name = absLink.substring(absLink.lastIndexOf("/"),end.intValue());
                try {
                    URL url = new URL(absLink);
                    BufferedImage img = ImageIO.read(url);
                    File file = new File("src/Images/" + name);
                    file.createNewFile();
                    ImageIO.write(img, "jpg", file);
                } catch (IOException e) {
                    e.printStackTrace();
                }

            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }


    }
}
