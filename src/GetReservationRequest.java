import java.util.List;

/**
 * Processes the get reservation request and creates the proper responses based on if there was an error or not.
 *
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

    // checks if any reservations were asked for or if the airport is in the database then returns the proper response
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
