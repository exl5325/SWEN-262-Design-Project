import java.util.List;

/**
 * Requests information from contained databases.  Holds most recent reservations for itinerary creation.
 *
 * Created by calvinclark on 10/8/17.
 */
public class DBFacade {
    private AirportDB airports;
    private FlightDB flights;
    private ReservationDB reservations;
    private List<Itinerary> itineraries;

    public DBFacade(){
        airports = new AirportDB();
        flights = new FlightDB();
        reservations = new ReservationDB();
    }

    //AirportDB methods
    public Airport findAirport(String code){
        return airports.findAirport(code);
    }
    public List<Airport> getAirports() {
        return airports.getAirports();
    }

    //FlightDB methods
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        itineraries = flights.findItineraries(origin, destination, numConnections, sortOrder);
        return itineraries;
    }

    //ReservationDB methods
    public boolean createReservation(String passenger, int id){
        return reservations.createReservation(passenger, itineraries.get(id));
    }
    public boolean deleteReservation(String passenger, String origin, String destination) {
        return reservations.deleteReservation(passenger, origin, destination);
    }
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        return reservations.findReservations(passenger, origin, destination);
    }
}
