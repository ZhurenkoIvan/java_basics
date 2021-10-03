import core.Line;
import core.Station;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

public class TestRouteCalculator extends TestCase {

    StationIndex spbMetroMini = new StationIndex();
    RouteCalculator routeCalculator = new RouteCalculator(spbMetroMini);;

    @Override
    protected void setUp() throws Exception {
        List<Line> lines = new ArrayList<>();
        lines.add(null);
        lines.add(new Line(1, "Первая"));
        lines.add(new Line(2, "Вторая"));
        lines.add(new Line(3, "Третья"));

        spbMetroMini.addLine(lines.get(1));
        spbMetroMini.addLine(lines.get(2));
        spbMetroMini.addLine(lines.get(3));

        spbMetroMini.addStation(new Station("Чернышевская", lines.get(1)));
        spbMetroMini.addStation(new Station("Площадь восстания", lines.get(1)));
        spbMetroMini.addStation(new Station("Площадь Ленина", lines.get(1)));
        spbMetroMini.addStation(new Station("Маяковская", lines.get(3)));
        spbMetroMini.addStation(new Station("Гостиный двор", lines.get(3)));
        spbMetroMini.addStation(new Station("Василеостровская", lines.get(3)));
        spbMetroMini.addStation(new Station("Петроградская", lines.get(2)));
        spbMetroMini.addStation(new Station("Горьковская", lines.get(2)));
        spbMetroMini.addStation(new Station("Невский проспект", lines.get(2)));

        lines.get(1).addStation(spbMetroMini.getStation("Чернышевская"));
        lines.get(1).addStation(spbMetroMini.getStation("Площадь восстания"));
        lines.get(1).addStation(spbMetroMini.getStation("Площадь Ленина"));
        lines.get(2).addStation(spbMetroMini.getStation("Петроградская"));
        lines.get(2).addStation(spbMetroMini.getStation("Горьковская"));
        lines.get(2).addStation(spbMetroMini.getStation("Невский проспект"));
        lines.get(3).addStation(spbMetroMini.getStation("Маяковская"));
        lines.get(3).addStation(spbMetroMini.getStation("Гостиный двор"));
        lines.get(3).addStation(spbMetroMini.getStation("Василеостровская"));


        List<Station> connection1_3 = new ArrayList<>();
        connection1_3.add(spbMetroMini.getStation("Площадь Восстания"));
        connection1_3.add(spbMetroMini.getStation("Маяковская"));
        spbMetroMini.addConnection(connection1_3);

        List<Station> connection2_3 = new ArrayList<>();
        connection2_3.add(spbMetroMini.getStation("Гостиный двор"));
        connection2_3.add(spbMetroMini.getStation("Невский проспект"));
        spbMetroMini.addConnection(connection2_3);

    }

    public void testGetShortestRoute() {
        //Проверка маршрута с двумя пересадками
        List<Station> actual = routeCalculator.getShortestRoute(spbMetroMini.getStation("Чернышевская"), spbMetroMini.getStation("Горьковская"));
        List<Station> expected = new ArrayList<>();
        expected.add(spbMetroMini.getStation("Чернышевская"));
        expected.add(spbMetroMini.getStation("Площадь Восстания"));
        expected.add(spbMetroMini.getStation("Маяковская"));
        expected.add(spbMetroMini.getStation("Гостиный двор"));
        expected.add(spbMetroMini.getStation("Невский проспект"));
        expected.add(spbMetroMini.getStation("Горьковская"));
        assertEquals(expected, actual);
    }

    public void testGetRouteWithOneConnection() {
        List<Station> actual = routeCalculator.getShortestRoute(spbMetroMini.getStation("Чернышевская"), spbMetroMini.getStation("Гостиный двор"));
        List<Station> expected = new ArrayList<>();
        expected.add(spbMetroMini.getStation("Чернышевская"));
        expected.add(spbMetroMini.getStation("Площадь Восстания"));
        expected.add(spbMetroMini.getStation("Маяковская"));
        expected.add(spbMetroMini.getStation("Гостиный двор"));
        assertEquals(expected, actual);
    }

    public void testGetRouteOnTheLine() {
        int actual = routeCalculator.getShortestRoute(spbMetroMini.getStation("Чернышевская"), spbMetroMini.getStation("Площадь Ленина")).size();
        int expected = 3;
        assertEquals(expected, actual);
    }

    public void testCalculateDuration() {
        double actual = RouteCalculator.calculateDuration(routeCalculator.getShortestRoute(spbMetroMini.getStation("Чернышевская"),
                                                          spbMetroMini.getStation("Горьковская")));
        double expected = 14.5;
        assertEquals(expected, actual);

    }
}
