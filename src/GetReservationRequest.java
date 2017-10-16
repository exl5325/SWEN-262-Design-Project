import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class GetReservationRequest implements Request {

    private String passenger, origin, destination;
    private DBFacade db;

    public GetReservationRequest(String passenger, String origin, String destination, DBFacade db){
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
        this.db = db;
    }

    @Override
    public Response request() {
        if (!origin.isEmpty() && db.findAirport(origin) == null)
            return new SimpleResponse("error,unknown origin");
        if (!destination.isEmpty() && db.findAirport(destination) == null)
            return new SimpleResponse("error,unknown destination");

        List<Reservation> reservations = db.findReservations(passenger, origin, destination);
        if(reservations.size()==0) {
            return new SimpleResponse("retrieve,0");
        }

        return new GetReservationsResponse(reservations);
    }
}
