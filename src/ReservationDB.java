import java.util.ArrayList;
import java.util.List;

/**
 * Holds, deletes, and searches reservations contained in the system.
 *
 * Created by calvinclark on 10/8/17.
 */
public class ReservationDB {
    private List<Reservation> reservations;
    public ReservationDB() {
        reservations = new ArrayList<>();
    }

    /**
     * Creates a new reservation with a given passenger name and itinerary.
     *
     * @param passenger the name of the passenger making the reservation
     * @param itinerary the itinerary the reservation is being made for
     * @return a boolean indicating if the reservation is created properly
     */
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

    /**
     * Deletes a reservation with a given passenger name, origin airport, and destination airport.
     *
     * @param passenger the passenger name on the reservation to be deleted
     * @param origin the origin airport code on the reservation to be deleted
     * @param destination the destination airport code on the reservation to be deleted
     * @return a boolean indicating that the reservation was properly deleted
     */
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

    /**
     * Searches for a reservation with a given passenger name, origin airport, and destination airport.
     *
     * @param passenger the passenger name on the reservation to search for
     * @param origin the origin airport code on the reservation to search for
     * @param destination the destination airport code on the reservation to search for
     * @return a list of all reservations that match the given values
     */
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
