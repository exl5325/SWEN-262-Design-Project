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
        int i = 0;
        for(Itinerary itinerary:itineraries){
            i++;
            response = response + "\n" + i + "," + itinerary.getAirfare() + "," + itinerary.getConnections();
            for(FlightInterface flight:itinerary.flights) {
                response = response + "," + flight.getFlightNumber() + "," + flight.getOrigin() + "," +
                        flight.getDepartureTime() + "," + flight.getDestination() + "," + flight.getArrivalTime();
            }
        }
        return response;
    }
}
