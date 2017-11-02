package Database;

import Itinerary.Itinerary;
import Itinerary.Airport;
import Itinerary.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Requests information from contained state, which can be either connected or disconnected from the server.
 *
 * Created by calvinclark on 10/8/17.
 */
public class DBFacade {
    private ConnectState state;
    private boolean connected;

    public DBFacade(){
        state = new Disconnected();
        connected = false;
    }

    //AirportDB methods
    public Airport findAirport(String code){
        return state.findAirport(code);
    }

    //FlightDB methods
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        return state.findItineraries(origin,destination,numConnections,sortOrder);
    }
    public int numberOfSavedItineraries() {
        return state.numberOfSavedItineraries();
    }

    //ReservationDB methods
    public boolean createReservation(String passenger, int id){
        return state.createReservation(passenger, id);
    }
    public boolean deleteReservation(String passenger, String origin, String destination) {
        return state.deleteReservation(passenger, origin, destination);
    }
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        return state.findReservations(passenger, origin, destination);
    }
    public boolean connect(){
        if(connected){
            return false;
        }
        else {
            state = new Connected();
            return true;
        }
    }

}
