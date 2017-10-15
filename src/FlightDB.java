import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by calvinclark on 10/8/17.
 */
public class FlightDB {
    private List<Flight> flights;
    private List<Itinerary> itineraries;
    private HashMap<String, Integer> connections = new HashMap<>();
    public FlightDB(){
        readFlights();
        readConnections();
        createItineraries();
    }

    public List<Itinerary> checkID(int id){
        List<Itinerary> foundItineraries = new ArrayList<>();
        for (Itinerary i : itineraries){
            if (i.getID() == id){
                foundItineraries.add(i);
            }
        }
        return foundItineraries;
    }

    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        List<Itinerary> foundItineraries = new ArrayList<>();
        Comparator<FlightInterface> comparator;
        if (numConnections <= 2) {
            for (Itinerary i : itineraries){
                if (i.getOrigin().equals(origin) && i.getDestination().equals(destination)
                        && i.getConnections() <= numConnections){
                    foundItineraries.add(i);
                }
            }
        }
        if(sortOrder.equals("arrival")){
            comparator = new ArrivalTimeComparator();
        }
        else if (sortOrder.equals("airfare")){
            comparator = new AirfareComparator();
        }
        else {
            comparator = new DepartureTimeComparator();
        }
        foundItineraries.sort(comparator);
        return foundItineraries;
    }

    private void readFlights(){
        flights = new ArrayList<>();
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
                    flights.add(f);
                }
            }

        }
        catch (FileNotFoundException f){
        }
    }

    private void createItineraries(){
        itineraries = new ArrayList<>();
        int id = 0;
        for(Flight f1 : flights){
            List<FlightInterface> oneFlight = new ArrayList<>();
            oneFlight.add(f1);
            itineraries.add(new Itinerary(oneFlight, id++));
            for(Flight f2 : flights){
                if(compatibleFlights(f1, f2)){
                    List<FlightInterface> twoFlights = new ArrayList<>();
                    twoFlights.add(f1);
                    twoFlights.add(f2);
                    itineraries.add(new Itinerary(twoFlights, id++));
                    for(Flight f3 : flights){
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

}
