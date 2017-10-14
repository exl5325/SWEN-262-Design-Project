import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class AirportDB {
    private HashMap<String, Airport> airports;
    public AirportDB(HashMap<String, Airport> airports){
        this.airports = airports;
    }
    public Airport findAirport(String code){
        return airports.get(code);
    }
    public List<Airport> getAirports(){ return new ArrayList<Airport>(airports.values()); }
}
