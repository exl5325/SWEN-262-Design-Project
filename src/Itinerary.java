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
     *
     * @return
     */
    public int getAirfare() {
        int sum = 0;
        for(FlightInterface f : flights) {
            sum += f.getAirfare();
        }
        return sum;
    }
    public String getArrivalTime(){
        return flights.get(0).getArrivalTime();
    }
    public String getDepartureTime(){
        return flights.get(flights.size()-1).getDepartureTime();
    }
    public String getOrigin() {
        return flights.get(0).getOrigin();
    }
    public String getDestination() {
        return flights.get(flights.size()-1).getDestination();
    }
    public int getConnections() {
        return flights.size() - 1;
    }
    public String getFlightNumber() {
        String fn = "";
        for(FlightInterface flight:flights){
            fn = fn + flight.getFlightNumber() + ",";
        }
        fn = fn.substring(0, fn.length() - 1);
        return fn;
    }
}
