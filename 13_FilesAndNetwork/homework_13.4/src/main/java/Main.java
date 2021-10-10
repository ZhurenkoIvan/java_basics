import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;


public class Main {
    public static void main(String[] args) {
        try {
            Document lentaHtml = Jsoup.connect("https://lenta.ru/").maxBodySize(0).get();
            Elements images = lentaHtml.select("img.g-picture");
            images.forEach(image -> {
                String absLink = image.attr("abs:src");
                System.out.println(absLink);
                String name = absLink.substring(absLink.lastIndexOf("/"));

                try {
                    URL url = new URL(absLink);
                    BufferedImage img = ImageIO.read(url);
                    File file = new File("src/Images/" + name);
                    file.createNewFile();
                    ImageIO.write(img, "jpg", file);
                } catch (Exception e) {
                    e.printStackTrace();
                }

            });
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
