import org.json.simple.JSONObject;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {
        try {
            //Объявляю переменные и при помощи селектора получаю нужный формат данных с сайта Московского метрополитена
            HashSet<HashMap<String,String>> connectionList = new HashSet<>();
            JSONObject JSONstations = new JSONObject();
            JSONObject JSONlines = new JSONObject();
            JSONObject JSONconnections = new JSONObject();
            Document moscowMetro = Jsoup.connect("https://www.moscowmap.ru/metro.html#lines").maxBodySize(0).get();
            Elements linesJsoup = moscowMetro.select("div #metrodata span.js-metro-line");

            Stream<Element> lines = linesJsoup.stream();
            //Добавляю JSONLines и создаю стрим номеров линий.

            lines.map(element -> {
                                JSONlines.put(element.text(), element.attr("data-line"));
                                return element.attr("data-line");
                                            })
                    //Использую номера линий, чтобы заполнить JSONstations и connectionList
                    .forEach(stationNumber -> {
                        JSONstations.put(stationNumber, getStationsOnLine(moscowMetro, stationNumber));
                        connectionList.addAll(getConnectionsOnLine(moscowMetro, stationNumber));
                    });
            //После формирования connectionList могу создать JSONconnections
            JSONconnections.putAll(getAllConnectionsJSON(connectionList));

            //Записываю объекты JSON на жесткий диск
            JSONObject JSONMoscowMetro = new JSONObject();
            JSONMoscowMetro.put("stations", JSONstations);
            JSONMoscowMetro.put("lines", JSONlines);
            JSONMoscowMetro.put("connections",JSONconnections);
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

    public static HashSet<HashMap<String,String>> getConnectionsOnLine (Document document, String line) {
        HashSet<HashMap<String,String>> connectionsList = new HashSet<>();
        Elements stationsAndTransition = document.select("div #metrodata [data-line=" + line + "] span.name," +
                                                                 " div #metrodata [data-line=" + line + "] span.t-icon-metroln");
        HashMap<String,String> connection = new HashMap<>();
        int count = 0;
        for (Element element : stationsAndTransition) {
            if (count==0) {
                count++;
                continue;
            }
            if (stationsAndTransition.get(count-1).hasText() && !element.hasText()){
                addConnectionOnSideLine(connection, element);
                connection.put(line,stationsAndTransition.get(count-1).text());
            } else if (!element.hasText()){
                addConnectionOnSideLine(connection, element);
            } else if (!stationsAndTransition.get(count-1).hasText() && element.hasText()){
                connectionsList.add(connection);
                connection = new HashMap<>();
            }
            count++;
        }
        return connectionsList;
    }
    private static void addConnectionOnSideLine(HashMap<String,String> connection, Element element) {
        String transitStation = element.attr("title");
        int start = transitStation.indexOf("«") + 1;
        int end = transitStation.indexOf("»");
        transitStation = transitStation.substring(start,end);
        String intermediateString = element.attr("class");
        String lineOfTransitStation = intermediateString.substring(intermediateString.lastIndexOf('-') + 1);;
        connection.put(lineOfTransitStation, transitStation);
    }

    private static JSONObject getAllConnectionsJSON (HashSet<HashMap<String,String>> connectionList) {
        JSONObject JSONconnections = new JSONObject();
        connectionList.forEach(connection -> {
            for (Object lineOfConnection : connection.keySet()) {
                JSONObject intermediateLine = new JSONObject();
                JSONObject intermediateStation = new JSONObject();
                intermediateLine.put("line", lineOfConnection);
                intermediateStation.put("station", connection.get(lineOfConnection));
                JSONconnections.put(intermediateLine, intermediateStation);
            }
        });
        return JSONconnections;
    }
}
