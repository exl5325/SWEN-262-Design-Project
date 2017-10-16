import java.util.*;

/**
 * Processes the make reservation request and creates the proper responses based on if there was an error or not.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class MakeReservationRequest implements Request {

    private DBFacade db;

    private String passenger;
    private int id;

    public MakeReservationRequest(String passenger, int id, DBFacade db){
        this.passenger = passenger;
        this.id = id;
        this.db = db;
    }

    // checks if the reservation was duplicated or if the id is invalid. Creates a response accordingly.
    @Override
    public Response request() {
        if (id <= 0 || db.numberOfSavedItineraries() < id) {
            return new SimpleResponse("error,invalid id");
        }
        if(db.createReservation(passenger,id)){
            return new SimpleResponse("reserve,successful");
        }
        return new SimpleResponse("error,duplicate reservation");
    }
}
