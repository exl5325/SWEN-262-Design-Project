import java.util.*;

/**
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
