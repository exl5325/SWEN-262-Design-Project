package Database;

import Itinerary.Itinerary;

import java.util.List;

/**
 * Created by calvinclark on 10/30/17.
 */
public interface FlightDB {
    List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder);
}
