import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class Itinerary implements FlightInterface {
    private List<FlightInterface> flights;
    private int ID;
    public Itinerary(List<FlightInterface> fs, int id){
        ID = id;
        flights = fs;
    }
    public int getAirfare() {
        int sum = 0;
        for(FlightInterface f : flights) {
            sum += f.getAirfare();
        }
        return sum;
    }
    public int getID(){ return ID; }
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
}
