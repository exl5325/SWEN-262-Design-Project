package Itinerary;

/**
 * Created by calvinclark on 10/8/17.
 */
public class Flight implements FlightInterface {
    private String flightNumber;
    private int airfare;
    private String arrival;
    private String departure;
    private String arrivalTime;
    private String departureTime;

    /**
     * Flight constructor
     * @param flightNumber flight number
     * @param airfare cost of flight
     * @param arrival arrival airport
     * @param departure departure airport
     * @param arrivalTime arrival time
     * @param departureTime departure time
     */
    public Flight(String flightNumber, int airfare, String arrival, String departure, String arrivalTime, String departureTime) {
        this.flightNumber = flightNumber;
        this.airfare = airfare;
        this.arrival = arrival;
        this.departure = departure;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    /**
     * Airfare getter
     * @return airfare
     */
    public int getAirfare(){
        return airfare;
    }

    /**
     * Arrival time getter
     * @return arrival time
     */
    public String getArrivalTime() {
        return arrivalTime;
    }

    /**
     * Departure time getter
     * @return departure time
     */
    public String getDepartureTime() {
        return departureTime;
    }

    /**
     * Origin airport getter
     * @return origin airport
     */
    public String getOrigin() {
        return departure;
    }

    /**
     * Destination airport getter
     * @return destination airport
     */
    public String getDestination() {
        return arrival;
    }

    /**
     * Flight number getter
     * @return flight number
     */
    public String getFlightNumber() {
        return flightNumber;
    }
}
