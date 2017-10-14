/**
 * Created by peter.audier on 10/8/2017.
 */
public class DeleteReservationRequest implements Request {

    private String passenger, origin, destination;

    public DeleteReservationRequest(String passenger, String origin, String destination){
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
    }

    @Override
    public Response request() {
        //TODO figure out how to keep track of if the reservation was present
        return new SimpleResponse("delete,successful");
    }
}
