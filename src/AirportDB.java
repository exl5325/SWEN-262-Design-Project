import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Created by calvinclark on 10/8/17.
 */
public class AirportDB {
    private HashMap<String, Airport> airports;

    public AirportDB(){
        readAirports();
    }

    public Airport findAirport(String code){
        return airports.get(code);
    }

    public List<Airport> getAirports(){ return new ArrayList<>(airports.values()); }
    private void readAirports(){
        airports = new HashMap<>();
        try {
            Scanner airportReader = new Scanner(new File("airports.txt"));
            Scanner delayReader = new Scanner(new File("delays.txt"));
            Scanner weatherReader = new Scanner(new File("weather.txt"));
            while (airportReader.hasNext()){
                String[] airportInfo = airportReader.nextLine().split(",");
                if(airportInfo.length >= 4) {
                    String code = airportInfo[0];
                    String name = airportInfo[1];
                    int delay = Integer.parseInt(delayReader.nextLine().split(",")[1]);
                    String[] weatherLine = weatherReader.nextLine().split(",");
                    String[] weather = Arrays.copyOfRange(weatherLine, 1, weatherLine.length - 1);
                    Airport airport = new Airport(code, name, delay, weather);
                    airports.put(code, airport);
                }
            }
        }
        catch (FileNotFoundException f) {
        }
    }
}
