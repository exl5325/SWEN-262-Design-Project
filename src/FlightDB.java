import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Reads and holds all instances of Flight from the specified CSV file, and creates/returns Itineraries using those
 * flights that match given parameters.
 *
 * Created by calvinclark on 10/8/17.
 */
public class FlightDB {
    private List<Flight> flights;
    private HashMap<String, Integer> connections = new HashMap<>();
    public FlightDB(){
        readFlights();
        readConnections();
    }

    /**
     * Creates all possible itineraries that match the given parameters for origin and destination airports,
     * maximum number of flight connections, and order in which to sort the found itineraries.
     *
     * @param origin the 3-letter code for the airport the itinerary departs from
     * @param destination the 3-letter code for the airport the itinerary ends at
     * @param numConnections the maximum number of connections (0-2) for the given flight
     * @param sortOrder a String specifying what the resulting itineraries should be sorted by
     * @return a list of all possible itineraries that match the given parameters
     */
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        List<Itinerary> foundItineraries = new ArrayList<>();
        Comparator<FlightInterface> comparator;
        if(numConnections >= 0){
            for(Flight f1 : flights){
                if(correctLocations(f1, origin, destination)){
                    List<FlightInterface> oneFlight = new ArrayList<>();
                    oneFlight.add(f1);
                    foundItineraries.add(new Itinerary(oneFlight));
                } else if (f1.getOrigin().equals(origin) && numConnections >= 1) {
                    for(Flight f2: flights){
                        if (compatibleFlights(f1, f2)) {
                            List<FlightInterface> twoFlights = new ArrayList<>();
                            twoFlights.add(f1);
                            twoFlights.add(f2);
                            Itinerary i = new Itinerary(twoFlights);
                            if (correctLocations(i, origin, destination)) {
                                foundItineraries.add(i);
                            } else {
                                for(Flight f3: flights){
                                    if(compatibleFlights(f2, f3)){
                                        List<FlightInterface> threeFlights = new ArrayList<>();
                                        threeFlights.add(f1);
                                        threeFlights.add(f2);
                                        threeFlights.add(f3);
                                        Itinerary i2 = new Itinerary(threeFlights);
                                        if(correctLocations(i2, origin, destination)){
                                            foundItineraries.add(i2);
                                        }
                                    }
                                }
                            }
                        }
                    }
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

    /**
     * Reads a CSV file named "flights.txt" and creates instances of a Flight object.  Stores those instances in a
     * List within the class.
     */
    private void readFlights(){
        flights = new ArrayList<>();

        String originKey = "origin";
        String destinationKey = "destination";
        String departureKey = "departure";
        String arrivalKey = "arrival";
        String flightNumberKey = "flightNumber";
        String airfareKey = "airfare";

        CSVCoder coder = new CSVCoder();
        List<Map<String, String>> flightData = coder.readListFromFile("src/flights",
                new String[]{originKey, destinationKey, departureKey, arrivalKey, flightNumberKey, airfareKey});

        for (Map<String, String> flightHash : flightData) {
            String origin = flightHash.get(originKey);
            String destination = flightHash.get(destinationKey);
            String departureTime = flightHash.get(departureKey);
            String arrivalTime = flightHash.get(arrivalKey);
            String flightNumber = flightHash.get(flightNumberKey);
            int airfare = Integer.parseInt(flightHash.get(airfareKey));
            Flight flight = new Flight(flightNumber, airfare, origin, destination, arrivalTime, departureTime);
            flights.add(flight);
        }
    }

    /**
     * Checks if two flights are able to be connected, meaning that they can be next to one another in an Itinerary.
     * @param flight1 the earlier flight in the Itinerary
     * @param flight2 the later flight in the Itinerary
     * @return a boolean value indicating whether f2 can be placed after f1 in an Itinerary
     */
    private boolean compatibleFlights(Flight flight1, Flight flight2){
        if(flight1.getDestination().equals(flight2.getOrigin())){
            String[] a1 = new String[2];
            String[] a2 = new String[2];
            a1[1] = flight1.getArrivalTime();
            a2[1] = flight2.getDepartureTime();
            int delayTime = TimeHelper.calculateMinutes(a2) - TimeHelper.calculateMinutes(a1);
            return(connections.get(flight1.getDestination()) <= delayTime);
        }
        return false;
    }

    /**
     * Checks if a given FlightInterface matches a given origin/destination pair
     * @param f1 The FlightInterface to be checked
     * @param origin the requested departure airport
     * @param destination the requested arrival airport
     * @return a boolean indicating whether f1 leaves from origin and arrives at destination
     */
    private boolean correctLocations(FlightInterface f1, String origin, String destination){
        return f1.getOrigin().equals(origin) && f1.getDestination().equals(destination);
    }

    /**
     * Reads in a CSV file named "connections.txt".  Converts it to a HashMap mapping airport codes to connection times.
     */
    private void readConnections(){
        String airportCodeKey = "airportCode";
        String timeKey = "minutes";

        CSVCoder coder = new CSVCoder();
        Map<String, Map<String, String>> connectionData = coder.readMapFromFile("src/connections",
                new String[]{airportCodeKey, timeKey});

        for (Map<String, String> connectionHash : connectionData.values()) {
            String code = connectionHash.get(airportCodeKey);
            Integer delayTime = Integer.parseInt(connectionHash.get(timeKey));
            connections.put(code, delayTime);
        }
    }

}
