package Database;

import Itinerary.Itinerary;

import java.util.List;

/**
 * A common interface to be implemented by classes that create itineraries
 * Makes future edits such as a possible WebFlightDB simpler
 *
 * Created by calvinclark on 10/30/17.
 */
public interface FlightDB {
    /**
     *
     * @param origin The 3-letter code representing the origin airport
     * @param destination  The 3-letter code representing the destination airport
     * @param numConnections  The maximum number of possible connections
     * @param sortOrder  A string representing the desired order to return the itineraries in
     * @return  A sorted list of matching itineraries
     */
    List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder);
}
