package Database;

import Itinerary.Itinerary;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calvinclark on 10/30/17.
 */
public class WebFlightDB implements FlightDB {
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        //TODO: Find matching itineraries

        return new ArrayList<Itinerary>();
    }
}
