package Itinerary;

/**
 * Contains a passenger's reservation for a specified itinerary.  Can retrieve data about the passenger or itinerary.
 *
 * Created by calvinclark on 10/8/17.
 */
public class Reservation {
    private String passenger;
    private Itinerary itinerary;

    /**
     * Holds a passenger name and an itinerary.
     *
     * @param passenger the passenger's name making the itinerary
     * @param itinerary the itinerary the passenger is making a reservation for
     */
    public Reservation(String passenger, Itinerary itinerary) {
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    /**
     * Retrieves the passenger's name who created the reservation.
     *
     * @return passenger name
     */
    public String getPassenger(){
        return passenger;
    }

    /**
     * Retrieves the itinerary contained within the reservation.
     *
     * @return itinerary the reservation was made for
     */
    public Itinerary getItinerary(){
        return itinerary;
    }

    /**
     * Retrieves the origin airport for the reservation's itinerary.
     *
     * @return 3-letter airport code for origin airport
     */
    public String getOrigin() {
        return itinerary.getOrigin();
    }

    /**
     * Retrieves the destination airport for the reservation's itinerary.
     *
     * @return 3-letter airport code for destination airport
     */
    public String getDestination() {
        return itinerary.getDestination();
    }

    /**
     * Checks if a given passenger name, origin code, and destination code match the current reservation.
     *
     * @param passenger the passenger name to be checked against the current reservation
     * @param origin the 3-letter airport code for the origin airport to be checked
     * @param destination the 3-letter airport code for the destination airport to be checked
     * @return a boolean indicating if the given fields match this reservation
     */
    public boolean sameReservation(String passenger, String origin, String destination){
        if(getPassenger().equals(passenger) && getOrigin().equals(origin) && getDestination().equals(destination)){
            return true;
        }
        return false;
    }
}
