/**
 * Created by peter.audier on 10/8/2017.
 */
public class AirportInfoRequest implements Request{

    private String airportCode;
    private AirportDB database;

    public AirportInfoRequest(String code, AirportDB db){
        airportCode = code;
        database = db;
    }

    @Override
    public Response request() {
        if(database.findAirport(airportCode)==null)
            return new SimpleResponse("error,unknown airport");
        return new AirportInfoResponse(database.findAirport(airportCode));
    }
}
