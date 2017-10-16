package Itinerary;

/**
 * A common interface to be implemented by Flight and Itinerary.
 *
 * Created by calvinclark on 10/8/17.
 */
public interface FlightInterface {
    /**
     * Calculates total airfare.
     *
     * @return the airfare in USD
     */
    int getAirfare();

    /**
     * Calculates overall arrival time.
     *
     * @return a formatted arrival time String
     */
    String getArrivalTime();

    /**
     * Calculates overall departure time.
     *
     * @return a formatted departure time string
     */
    String getDepartureTime();

    /**
     * Finds the origin airport and returns its 3-letter code.
     *
     * @return a 3-letter airport code String
     */
    String getOrigin();

    /**
     * Finds the destination airport and returns its 3-letter code.
     *
     * @return a 3-letter airport code String
     */
    String getDestination();

    /**
     * Returns a formatted String representing flight number(s).
     *
     * @return a String containing one or many flight numbers
     */
    String getFlightNumber();
}
