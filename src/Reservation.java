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
}
