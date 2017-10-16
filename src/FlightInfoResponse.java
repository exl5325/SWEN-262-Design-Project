import java.util.List;

/**
 * Creates the response for flight info based on a list of itineraries.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class FlightInfoResponse implements Response {

    private List<Itinerary> itineraries;

    public FlightInfoResponse(List<Itinerary> i){
        itineraries = i;
    }

    //formats the string for the flight info response based on a list of itineraries
    @Override
    public String outputData() {
        String response = "info," + itineraries.size() + "\n";
        if(itineraries.size()==0)
            return response;
        int i = 0;
        for(Itinerary itinerary:itineraries){
            i++;
            if(response.equals(""))
                response = response + i + "," + itinerary.getAirfare() + "," + itinerary.getConnections();
            else
                response = response + "\n" + i + "," + itinerary.getAirfare() + "," + itinerary.getConnections();
            for(FlightInterface flight:itinerary.flights) {
                response = response + "," + flight.getFlightNumber() + "," + flight.getOrigin() + "," +
                        flight.getDepartureTime() + "," + flight.getDestination() + "," + flight.getArrivalTime();
            }
        }
        return response;
    }
}
