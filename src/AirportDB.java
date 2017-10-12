import java.util.HashMap;

/**
 * Created by calvinclark on 10/8/17.
 */
public class AirportDB {
    private HashMap<String, Airport> airports;
    public AirportDB(){
        airports = new HashMap<>();
    }
    public Airport findAirport(String code){
        return airports.get(code);
    }
}
