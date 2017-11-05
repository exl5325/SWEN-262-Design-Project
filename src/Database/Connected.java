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
    private boolean flights_connected;
    private boolean airports_connected;

    public Connected(){
        airports = new LocalAirportDB();
        flights = new LocalFlightDB();
        reservations = ReservationDB.shared;
        itineraries = new ArrayList<>();
        flights_connected = false;
        airports_connected = false;
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
    public Itinerary savedItineraryWithId(int id) {
        return itineraries.get(id - 1);
    }

    //ReservationDB methods
    public boolean createReservation(String passenger, int id){
        if(id > itineraries.size()){
            return false;
        }
        return createReservation(passenger, savedItineraryWithId(id));
    }
    public boolean createReservation(String passenger, Itinerary itinerary) {
        return reservations.createReservation(passenger, itinerary);
    }

    public boolean deleteReservation(String passenger, String origin, String destination) {
        return reservations.deleteReservation(passenger, origin, destination);
    }
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        return reservations.findReservations(passenger, origin, destination);
    }
    public boolean connectAirports(){
        if(!airports_connected) {
            airports = new WebAirportDB();
            airports_connected = true;
            return true;
        }
        return false;
    }
    public boolean disconnectFlights(){
        if(flights_connected) {
            flights = new LocalFlightDB();
            flights_connected = false;
            return true;
        }
        return false;
    }
    public boolean disconnectAirports(){
        if(airports_connected) {
            airports = new LocalAirportDB();
            airports_connected = false;
            return true;
        }
        return false;
    }
}
