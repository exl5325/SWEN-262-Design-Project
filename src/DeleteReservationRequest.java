/**
 * Created by peter.audier on 10/8/2017.
 */
public class DeleteReservationRequest implements Request {

    private String passenger, origin, destination;
    private DBFacade db;

    public DeleteReservationRequest(String passenger, String origin, String destination, DBFacade db){
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
        this.db = db;
    }

    @Override
    public Response request() {
        if(db.deleteReservation(passenger, origin, destination))
            return new SimpleResponse("delete,successful");
        return new SimpleResponse("error,reservation not found");
    }
}
