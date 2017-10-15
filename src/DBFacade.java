import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class DBFacade {
    private AirportDB airports;
    private FlightDB flights;
    private ReservationDB reservations;

    public DBFacade(){
        airports = new AirportDB();
        flights = new FlightDB();
        reservations = new ReservationDB();
    }

    //AirportDB methods
    public Airport findAirport(String code){
        return airports.findAirport(code);
    }
    public List<Airport> getAirports(){
        return airports.getAirports();
    }

    //FlightDB methods
    List<Itinerary> checkID(int id){
        return flights.checkID(id);
    }
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        return flights.findItineraries(origin, destination, numConnections, sortOrder);
    }

    //ReservationDB methods
    public boolean createReservation(String passenger, Itinerary itinerary){
        return reservations.createReservation(passenger, itinerary);
    }
    public boolean deleteReservation(String passenger, String origin, String destination) {
        return reservations.deleteReservation(passenger, origin, destination);
    }
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        return reservations.findReservations(passenger, origin, destination);
    }
}
