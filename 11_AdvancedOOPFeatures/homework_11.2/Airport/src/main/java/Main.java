import com.skillbox.airport.Airport;
import com.skillbox.airport.Flight;
import com.skillbox.airport.Terminal;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {


    }

    public static List<Flight> findPlanesLeavingInTheNextTwoHours(Airport airport) {
        ArrayList<Flight> allPlanesLeavingInTheNextTwoHours = new ArrayList<>();
        Date currentTime = new Date();
        Date twoHoursLater = new Date(System.currentTimeMillis() + 7200000);
        List<Terminal> allTerminals = airport.getTerminals();
        for (Terminal terminal : allTerminals) {
            ArrayList<Flight> stream = terminal.getFlights().stream()
                    .filter(flight -> flight.getDate().after(currentTime) && flight.getDate().before(twoHoursLater))
                    .filter(flight -> flight.getType().equals(Flight.Type.DEPARTURE))
                    .collect(Collectors.toCollection(ArrayList::new));
            allPlanesLeavingInTheNextTwoHours.addAll(stream);

        }
        //TODO Метод должден вернуть список рейсов вылетающих в ближайшие два часа.
        return allPlanesLeavingInTheNextTwoHours;
    }

}