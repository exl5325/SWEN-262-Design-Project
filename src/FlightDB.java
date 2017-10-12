import java.util.ArrayList;
import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class FlightDB {
    private List<Flight> flights;
    private List<Itinerary> itineraries;
    public FlightDB(){
        flights = new ArrayList<>();
    }
    public List<Itinerary> findItineraries(String origin, String destination, int numConnections, String sortOrder){
        List<Itinerary> foundItineraries = new ArrayList<>();
        if (numConnections <= 2) {
            for (Itinerary i : itineraries){
                if (i.getOrigin().equals(origin) && i.getDestination().equals(destination)
                        && i.getConnections() <= numConnections){
                    foundItineraries.add(i);
                }
            }
        }
        return foundItineraries;
    }

}
