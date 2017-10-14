import java.util.*;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class MakeReservationRequest implements Request {

    private ReservationDB resDatabase;
    private FlightDB itinDatabase;

    private String passenger, origin, destination;
    private int id;

    public MakeReservationRequest(String passenger, int id, String origin, String destination){
        this.passenger = passenger;
        this.id = id;
        this.destination = destination;
        this.origin = origin;
    }

    @Override
    public Response request() {

        //TODO create the reservation? will it be passed in?

        List<Reservation> reservations = new ArrayList<Reservation>();
        for(Reservation res:resDatabase.findReservations(passenger, origin, destination)) {
            if (itinDatabase.checkID(res.getItinerary().getID()).size() == 0)
                return new SimpleResponse("error,invalid id");
            reservations.add(res);
        }
        if(reservations.size()==0)
            return new SimpleResponse("error,duplicate reservation");

        return new SimpleResponse("reserve,successful");
    }
}
