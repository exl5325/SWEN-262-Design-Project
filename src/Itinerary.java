import java.util.List;

/**
 * Contains several instances of FlightInterface.  Treats combined flights as a single FlightInterface.
 *
 * Created by calvinclark on 10/8/17.
 */
public class Itinerary implements FlightInterface {
    public List<FlightInterface> flights;
    public Itinerary(List<FlightInterface> fs){
        flights = fs;
    }

    /**
     * Calculates total airfare for the Itinerary.
     *
     * @return sum of all flights' airfares
     */
    public int getAirfare() {
        int sum = 0;
        for(FlightInterface f : flights) {
            sum += f.getAirfare();
        }
        return sum;
    }

    /**
     * Returns arrival time for the last flight in the Itinerary.  This represents the arrival time for the
     * itinerary as a whole.
     *
     * @return arrival time String
     */
    public String getArrivalTime(){
        return flights.get(flights.size()-1).getArrivalTime();
    }

    /**
     * Returns departure time for the first flight in the Itinerary.  This represents the departure time for the
     * itinerary as a whole.
     *
     * @return departure time String
     */
    public String getDepartureTime(){
        return flights.get(0).getDepartureTime();
    }

    /**
     * Returns the 3-letter code representing the origin airport of the first flight in the itinerary.
     *
     * @return 3-letter origin Airport code string
     */
    public String getOrigin() {
        return flights.get(0).getOrigin();
    }

    /**
     * Returns the 3-letter code representing the destination airport of the last flight in the itinerary.
     *
     * @return 3-letter destination Airport code string
     */
    public String getDestination() {
        return flights.get(flights.size()-1).getDestination();
    }

    /**
     * Returns the number of connections within the itinerary.  This will be 1 less than the total number of flights.
     *
     * @return int of total connections
     */
    public int getConnections() {
        return flights.size() - 1;
    }

    /**
     * Concatenates all the flight numbers in the itinerary.
     *
     * @return the total formatted string
     */
    public String getFlightNumber() {
        String fn = "";
        for(FlightInterface flight:flights){
            fn = fn + flight.getFlightNumber() + ",";
        }
        fn = fn.substring(0, fn.length() - 1);
        return fn;
    }
}
