import java.util.*;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class MakeReservationRequest implements Request {

    private ReservationDB resDatabase;
    private FlightDB itinDatabase;

    private String passenger;
    private int id;

    public MakeReservationRequest(String passenger, int id){
        this.passenger = passenger;
        this.id = id;
    }

    @Override
    public Response request() {

        //TODO create the reservation? will it be passed in?

        List<Reservation> reservations = new ArrayList<Reservation>();
        for(Reservation res:resDatabase.findReservations(passenger, "fill in later!", "fill in later!")) {
                                                                                    //TODO fill in ^
            if (itinDatabase.checkID(res.getItinerary().getID()).size() == 0)
                return new SimpleResponse("error,invalid id");
            reservations.add(res);
        }
        if(reservations.size()==0)
            return new SimpleResponse("error,duplicate reservation");

        return new SimpleResponse("reserve,successful");
    }
}
