import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class GetReservationsResponse implements Response {

    private List<Reservation> reservation;

    public GetReservationsResponse(List<Reservation> reservation){
        this.reservation = reservation;
    }

    @Override
    public String outputData() {
        String response = "retrieve,";
        for(Reservation res:reservation){
            Itinerary itinerary = res.getItinerary();
            response = response + "\n" + itinerary.getAirfare() + "," + itinerary.getConnections();
            for(FlightInterface flight:itinerary.flights) {
                response = response + "," + flight.getFlightNumber() + "," + flight.getOrigin() + "," +
                        flight.getDepartureTime() + "," + flight.getDestination() + "," + flight.getArrivalTime();
            }
        }
        return  response;
    }
}
