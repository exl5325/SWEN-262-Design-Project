import java.util.ArrayList;
import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class ReservationDB {
    private List<Reservation> reservations;
    public ReservationDB() {
        reservations = new ArrayList<>();
    }

    public boolean createReservation(String passenger, Itinerary itinerary){
        Reservation newReservation = new Reservation(passenger, itinerary);
        if (reservations.contains(newReservation)){
            return false;
        }
        else {
            reservations.add(newReservation);
            return true;
        }
    }

    public boolean deleteReservation(String passenger, String origin, String destination) {
        for(int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            if(r.sameReservation(passenger, origin, destination)){
                reservations.remove(i);
                return true;
            }
        }
        return false;
    }

    public List<Reservation> findReservations(String passenger, String origin, String destination){
        List<Reservation> found = new ArrayList<>();
        for(Reservation r: reservations){
            if (r.sameReservation(passenger, origin, destination)){
                found.add(r);
            }
        }
        return reservations;
    }

}
