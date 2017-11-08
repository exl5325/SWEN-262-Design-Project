package Database;

import Itinerary.Airport;
import Itinerary.Itinerary;
import Itinerary.Reservation;

import java.util.List;

/**
 * A state representing when DBManager is disconnected from the AFRS system
 *
 * Created by calvinclark on 11/2/17.
 */
public class Disconnected implements ConnectState {
    public Airport findAirport(String code){
        return null;
    }
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        return null;
    }
    public int numberOfSavedItineraries(){
        return 0;
    }
    public Itinerary savedItineraryWithId(int id) {
        return null;
    }

    public boolean createReservation(String passenger, int id){
        return false;
    }
    public boolean createReservation(String passenger, Itinerary itinerary) { return false; }
    public boolean deleteReservation(String passenger, String origin, String destination){
        return false;
    }
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        return null;
    }
    public boolean connectAirports(){
        return false;
    }
    public boolean disconnectAirports(){
        return false;
    }
}
