package RequestResponse;

import Database.DBFacade;

/**
 * Processes the airport info request and creates the proper responses based on if there was an error or not.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class AirportInfoRequest implements Request{

    private String airportCode;
    private DBFacade db;

    public AirportInfoRequest(String code, DBFacade db){
        airportCode = code;
        this.db = db;
    }

    @Override
    public Response request() {
        if(db.findAirport(airportCode)==null)
            return new SimpleResponse("error,unknown airport");
        return new AirportInfoResponse(db.findAirport(airportCode));
    }
}
