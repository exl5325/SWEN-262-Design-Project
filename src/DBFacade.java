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
    private List<Flight> flightList;
    private HashMap<String, Integer> connections = new HashMap<>();
    public DBFacade(){
        airports = new AirportDB(readAirports());
        readFlights();
        readConnections();
        flights = new FlightDB(flightList, createItineraries());
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
                if(airportInfo.length >= 4) {
                    String code = airportInfo[0];
                    String name = airportInfo[1];
                    int delay = Integer.parseInt(delayReader.nextLine().split(",")[1]);
                    String[] weatherLine = weatherReader.nextLine().split(",");
                    String[] weather = Arrays.copyOfRange(weatherLine, 1, weatherLine.length - 1);
                    Airport airport = new Airport(code, name, delay, weather);
                    read.put(code, airport);
                }
            }
        }
        catch (FileNotFoundException f) {
        }
        return read;
    }

    private void readFlights(){
        flightList = new ArrayList<>();
        try {
            Scanner flightReader = new Scanner(new File("flights.txt"));
            while(flightReader.hasNext()) {
                String[] flightLine = flightReader.nextLine().split(",");
                if(flightLine.length == 6) {
                    String origin = flightLine[0];
                    String destination = flightLine[1];
                    String departureTime = flightLine[2];
                    String arrivalTime = flightLine[3];
                    String flightNumber = flightLine[4];
                    int airfare = Integer.parseInt(flightLine[5]);
                    Flight f = new Flight(flightNumber, airfare, origin, destination, arrivalTime, departureTime);
                    flightList.add(f);
                }
            }

        }
        catch (FileNotFoundException f){
        }
    }

    private List<Itinerary> createItineraries(){
        int id = 0;
        List<Itinerary> itineraries = new ArrayList<>();
        for(Flight f1 : flightList){
            List<FlightInterface> oneFlight = new ArrayList<>();
            oneFlight.add(f1);
            itineraries.add(new Itinerary(oneFlight, id++));
            for(Flight f2 : flightList){
                if(compatibleFlights(f1, f2)){
                    List<FlightInterface> twoFlights = new ArrayList<>();
                    twoFlights.add(f1);
                    twoFlights.add(f2);
                    itineraries.add(new Itinerary(twoFlights, id++));
                    for(Flight f3 : flightList){
                        if(compatibleFlights(f2, f3)){
                            List<FlightInterface> threeFlights = new ArrayList<>();
                            threeFlights.add(f1);
                            threeFlights.add(f2);
                            threeFlights.add(f3);
                            itineraries.add(new Itinerary(threeFlights, id++));
                        }
                    }
                }
            }
        }
        return itineraries;
    }

    private boolean compatibleFlights(Flight flight1, Flight flight2){
        if(flight1.getDestination() == flight2.getOrigin()){
            String[] a1 = new String[2];
            String[] a2 = new String[2];
            a1[1] = flight1.getArrivalTime();
            a2[1] = flight2.getDepartureTime();
            int delayTime = TimeHelper.calculateMinutes(a2) - TimeHelper.calculateMinutes(a1);
            return(connections.get(flight1.getDestination()) <= delayTime);
        }
        return false;
    }

    private void readConnections(){
        try {
            Scanner connectionReader = new Scanner(new File("connections.txt"));
            while(connectionReader.hasNext()){
                String[] connection = connectionReader.nextLine().split(",");
                if(connection.length == 2){
                    String code = connection[0];
                    Integer delayTime = Integer.parseInt(connection[1]);
                    connections.put(code, delayTime);
                }
            }
        }
        catch (FileNotFoundException f){
        }
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
