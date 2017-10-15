import java.util.*;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class MakeReservationRequest implements Request {

    private ReservationDB resDatabase;
    private DBFacade db;

    private String passenger, origin, destination;
    private int id;

    public MakeReservationRequest(String passenger, int id, String origin, String destination, DBFacade db){
        this.passenger = passenger;
        this.id = id;
        this.destination = destination;
        this.origin = origin;
        this.db = db;
    }

    @Override
    public Response request() {
        if(db.findReservations(passenger,origin,destination).size()!=0){
            return new SimpleResponse("error,duplicate reservation");
        }
        if(db.createReservation(passenger,id)){
            return new SimpleResponse("reserve,successful");
        }
        return new SimpleResponse("error,duplicate reservation");
    }
}
