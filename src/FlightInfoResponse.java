import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class FlightInfoResponse implements Response {

    private List<Itinerary> itineraries;

    public FlightInfoResponse(List<Itinerary> i){
        itineraries = i;
    }

    @Override
    public String outputData() {
        String response = "info," + itineraries.size();
        if(itineraries.size()==0)
            return response;
        for(Itinerary itinerary:itineraries){
            response = response + "<nl>" + itinerary.getID() + itinerary.getAirfare() + "," + itinerary.getConnections() + "," +
                       //TODO add flight number
                       itinerary.getOrigin() + "," + itinerary.getDepartureTime() + "," + itinerary.getDestination() +
                       "," + itinerary.getArrivalTime();
        }
        return response;
    }
}
