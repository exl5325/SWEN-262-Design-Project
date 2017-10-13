/**
 * Created by calvinclark on 10/8/17.
 */
public class Flight implements FlightInterface {
    private int flightNumber;
    private int airfare;
    private String arrival;
    private String departure;
    private String arrivalTime;
    private String departureTime;

    public Flight(int flightNumber, int airfare, String arrival, String departure, String arrivalTime, String departureTime) {
        this.flightNumber = flightNumber;
        this.airfare = airfare;
        this.arrival = arrival;
        this.departure = departure;
        this.arrivalTime = arrivalTime;
        this.departureTime = departureTime;
    }

    public int getAirfare(){
        return airfare;
    }
    public String getArrivalTime() {
        return arrivalTime;
    }
    public String getDepartureTime() {
        return departureTime;
    }
    public String getOrigin() {
        return departure;
    }
    public String getDestination() {
        return arrival;
    }
}
