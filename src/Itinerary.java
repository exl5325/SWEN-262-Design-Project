import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class Itinerary implements FlightInterface {
    private List<Flight> flights;
    public int getAirfare() {
        int sum = 0;
        for(Flight f : flights) {
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
}
