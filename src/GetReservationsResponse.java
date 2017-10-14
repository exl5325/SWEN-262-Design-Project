import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class GetReservationsResponse implements Response {

    private Reservation reservation;

    public GetReservationsResponse(Reservation reservation){
        this.reservation = reservation;
    }

    @Override
    public String outputData() {
        Itinerary itinerary = reservation.getItinerary();
        if(itinerary == null)
            return "retrieve,0";
        return "retrive,1<nl>" + itinerary.getAirfare() + "," + itinerary.getConnections() + "," +
                          //TODO add flight number
                          itinerary.getOrigin() + "," + itinerary.getDepartureTime() + "," + itinerary.getDestination()
                          + "," + itinerary.getArrivalTime();
    }
}
