/**
 * Created by calvinclark on 10/8/17.
 */
public class Reservation {
    private String passenger;
    private Itinerary itinerary;

    public Reservation(String passenger, Itinerary itinerary) {
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    public String getPassenger(){
        return passenger;
    }

    public String getOrigin() {
        return itinerary.getOrigin();
    }

    public String getDestination() {
        return itinerary.getDestination();
    }
    public boolean sameReservation(String passenger, String origin, String destination){
        if(getPassenger().equals(passenger) && getOrigin().equals(origin) && getDestination().equals(destination)){
            return true;
        }
        return false;
    }
}
