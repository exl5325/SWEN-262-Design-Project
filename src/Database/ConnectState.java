package Database;

import Itinerary.Airport;
import Itinerary.Itinerary;
import Itinerary.Reservation;

import java.util.List;

/**
 * Created by calvinclark on 11/2/17.
 */
public interface ConnectState {
    Airport findAirport(String code);
    List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder);
    int numberOfSavedItineraries();
    boolean createReservation(String passenger, int id);
    boolean deleteReservation(String passenger, String origin, String destination);
    List<Reservation> findReservations(String passenger, String origin, String destination);
    boolean connectFlights();
    boolean connectAirports();
}
