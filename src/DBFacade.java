import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class DBFacade {
    private AirportDB airports;
    private FlightDB flights;
    private ReservationDB reservations;
    public DBFacade(){
        airports = new AirportDB(readAirports());
        flights = new FlightDB(readFlights(), createItineraries());
        reservations = new ReservationDB();
    }
    private HashMap<String, Airport> readAirports(){
        HashMap<String, Airport> read = new HashMap<>();
        //TODO: read in airports from CSV file and add them to read
        return read;
    }
    private List<Flight> readFlights(){
        List<Flight> read = new ArrayList<>();
        //TODO: read in flights from CSV file and add them to read
        return read;
    }
    private List<Itinerary> createItineraries(){
        List<Itinerary> read = new ArrayList<>();
        //TODO: Create itineraries from flight and airport data
        return read;
    }
}
