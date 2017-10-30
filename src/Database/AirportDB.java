package Database;

import Itinerary.Airport;

/**
 * Created by calvinclark on 10/30/17.
 */
public interface AirportDB {
    Airport findAirport(String code);
}
