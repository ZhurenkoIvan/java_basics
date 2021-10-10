import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            JSONObject JSONstations = new JSONObject();
            JSONObject JSONlines = new JSONObject();
            List<String>  stationsNumbers = new ArrayList<>();
            Document moscowMetro = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements linesJsoup = moscowMetro.select("div #metrodata span.js-metro-line");
            linesJsoup.forEach(element -> {
                JSONlines.put(element.text(), element.attr("data-line"));
                stationsNumbers.add(element.attr("data-line"));
            });
            stationsNumbers.forEach(s -> JSONstations.put(s, getStationsOnLine(moscowMetro, s)));
            JSONObject JSONMoscowMetro = new JSONObject();
            JSONMoscowMetro.put("stations", JSONstations);
            JSONMoscowMetro.put("lines", JSONlines);
            writeJSONFile(JSONMoscowMetro, "src/main/resources/moscowMetro.json");




        }
        catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public static ArrayList<String> getStationsOnLine (Document document ,String line) {
        ArrayList<String> stations = new ArrayList<>();
        Elements stationsJsoup = document.select("div #metrodata [data-line=" + line + "] span.name");
        stationsJsoup.forEach(station -> stations.add(station.text()));
        return stations;
    }

    public static void writeJSONFile (JSONObject jsonObject, String path) throws Exception {
        File file = new File(path);
        file.createNewFile();
        FileWriter json = new FileWriter(file);
        json.write(jsonObject.toJSONString());
        json.flush();
        json.close();
    }
}
