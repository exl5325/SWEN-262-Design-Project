import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

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
        try {
            Scanner airportReader = new Scanner(new File("airports.txt"));
            Scanner delayReader = new Scanner(new File("delays.txt"));
            Scanner weatherReader = new Scanner(new File("weather.txt"));
            while (airportReader.hasNext()){
                String[] airportInfo = airportReader.nextLine().split(",");
                String code = airportInfo[0];
                String name = airportInfo[1];
                int delay = Integer.parseInt(delayReader.nextLine().split(",")[1]);
                String[] weatherLine = weatherReader.nextLine().split(",");
                String[] weather = Arrays.copyOfRange(weatherLine, 1, weatherLine.length-1);
                Airport airport = new Airport(code, name, delay, weather);
                read.put(code, airport);
            }
        }
        catch (FileNotFoundException f) {
        }
        return read;
    }

    private List<Flight> readFlights(){
        List<Flight> read = new ArrayList<>();
        try {
            Scanner flightReader = new Scanner(new File("flights.txt"));
            while(flightReader.hasNext()) {
                String[] flightLine = flightReader.nextLine().split(",");
                String origin = flightLine[0];
                String destination = flightLine[1];
                String departureTime = flightLine[2];
                String arrivalTime = flightLine[3];
                String flightNumber = flightLine[4];
                int airfare = Integer.parseInt(flightLine[5]);
                Flight f = new Flight(flightNumber, airfare, origin, destination, arrivalTime, departureTime);
                read.add(f);
            }

        }
        catch (FileNotFoundException f){
        }
        return read;
    }

    private List<Itinerary> createItineraries(){
        List<Itinerary> read = new ArrayList<>();
        //TODO: Create itineraries from flight and airport data
        return read;
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
