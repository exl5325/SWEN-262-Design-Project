package RequestResponse;

import Database.DBManager;
import Itinerary.Airport;

/**
 * Processes the airport info request and creates the proper responses based on if there was an error or not.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class AirportInfoRequest implements Request{

    private String airportCode;
    private DBManager db;

    public AirportInfoRequest(String code, DBManager db){
        airportCode = code;
        this.db = db;
    }

    /**
     * checks if the airport exists
     * @return the appropriate response
     */
    @Override
    public Response request() {
        Airport airport = db.findAirport((airportCode));
        if(airport==null)
            return new SimpleResponse("error,unknown airport");

        String delay = "";
        if (airport.getDelay() < 60) {
            delay = airport.getDelay() + " minutes";
        } else {
            delay = (airport.getDelay() / 60) + " hours and " + (airport.getDelay() % 60) + " minutes";
        }
        return new SimpleResponse("airport,"+airport.getName()+","+airport.getWeather()+","+delay);
    }
}
