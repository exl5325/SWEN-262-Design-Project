import java.util.List;

/**
 * Created by peter.audier on 10/14/2017.
 */
public class MakeReservationResponse implements Response{
    private List<Reservation> reservations;
    private ReservationDB resDatabase;
    private FlightDB itinDatabase;

    public MakeReservationResponse(List<Reservation> res, ReservationDB db1, FlightDB db2){
        reservations = res;
        resDatabase = db1;
        itinDatabase = db2;
    }

    @Override
    public String outputData() {
        for(Reservation res:reservations) {
            if (resDatabase.findReservations(res.getPassenger(), res.getOrigin(), res.getDestination()).size() > 0)
                return "error,duplicate reservation";
            if (itinDatabase.checkID(res.getItinerary().getID()).size() == 0)
                return "error,invalid id";
        }
        return "reserve,successful";
    }
}
