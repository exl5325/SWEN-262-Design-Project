package Test;

import Itinerary.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthewseaman on 10/16/17.
 */
public class ItineraryTests extends TestCase {

    public void testAirportWeather() {
        String weatherList = "sunny,85,rain,30,clear,70";
        String[] weather = weatherList.split(",");
        Airport airport = new Airport("BWI", "Baltimore", 0,
                weather);

        assertTrue(
                airport.getWeather().equals("sunny,85"),
                "Airport weather is not looping correctly"
        );

        assertTrue(
                airport.getWeather().equals("rain,30"),
                "Airport weather is not looping correctly"
        );

        assertTrue(
                airport.getWeather().equals("clear,70"),
                "Airport weather is not looping correctly"
        );

        assertTrue(
                airport.getWeather().equals("sunny,85"),
                "Airport weather is not looping correctly"
        );
    }

    public void testSameReservation() {
        Flight flight = new Flight("101", 200, "SFO", "BWI",
                "3:00p", "9:00a");
        List<FlightInterface> flights = new ArrayList<>();
        flights.add(flight);
        Reservation res1 = new Reservation("Matt", new Itinerary(flights));

        assertTrue(
                res1.sameReservation("Matt", "BWI", "SFO"),
                "Reservation equality is broken."
        );
    }

    public void testItinerarySorting() {
        Flight flight1 = new Flight("101", 400, "SFO", "BWI",
                "3:00p", "11:00a");
        Flight flight2 = new Flight("102", 200, "SFO", "BWI",
                "4:00p", "10:00a");
        Flight flight3 = new Flight("103", 500, "SFO", "BWI",
                "5:00p", "9:00a");

        List<Itinerary> itineraries = new ArrayList<>();

        List<FlightInterface> flights = new ArrayList<>();
        flights.add(flight1);
        Itinerary it1 = new Itinerary(flights);
        itineraries.add(it1);

        flights = new ArrayList<>();
        flights.add(flight2);
        Itinerary it2 = new Itinerary(flights);
        itineraries.add(it2);

        flights = new ArrayList<>();
        flights.add(flight3);
        Itinerary it3 = new Itinerary(flights);
        itineraries.add(it3);

        itineraries.sort(new AirfareComparator());
        assertTrue(
                itineraries.get(0) == it2,
                "Sorting itineraries by airfare is broken."
        );

        itineraries.sort(new ArrivalTimeComparator());
        assertTrue(
                itineraries.get(0) == it1,
                "Sorting itineraries by arrival time is broken."
        );

        itineraries.sort(new DepartureTimeComparator());
        assertTrue(
                itineraries.get(0) == it3,
                "Sorting itineraries by departure time is broken."
        );
    }

}
