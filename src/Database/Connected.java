package Database;

import Itinerary.Airport;
import Itinerary.Itinerary;
import Itinerary.Reservation;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by calvinclark on 11/2/17.
 */
public class Connected implements ConnectState {
    private AirportDB airports;
    private FlightDB flights;
    private ReservationDB reservations;
    private List<Itinerary> itineraries;

    public Connected(){
        airports = new LocalAirportDB();
        flights = new LocalFlightDB();
        reservations = new ReservationDB();
        itineraries = new ArrayList<>();
    }

    //AirportDB methods
    public Airport findAirport(String code){
        return airports.findAirport(code);
    }

    //FlightDB methods
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        itineraries = flights.findItineraries(origin, destination, numConnections, sortOrder);
        return itineraries;
    }
    public int numberOfSavedItineraries() {
        return itineraries.size();
    }

    //ReservationDB methods
    public boolean createReservation(String passenger, int id){
        if(id > itineraries.size()){
            return false;
        }
        return reservations.createReservation(passenger, itineraries.get(id - 1));
    }
    public boolean deleteReservation(String passenger, String origin, String destination) {
        return reservations.deleteReservation(passenger, origin, destination);
    }
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        return reservations.findReservations(passenger, origin, destination);
    }
    public boolean connectFlights(){
        flights = new WebFlightDB();
        return true;
    }
    public boolean connectAirports(){
        airports = new WebAirportDB();
        return true;
    }
}
