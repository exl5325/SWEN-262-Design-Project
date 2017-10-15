import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

/**
 * Populates, stores, and manages a HashMap with airport data.
 *
 * Created by calvinclark on 10/8/17.
 */
public class AirportDB {
    private HashMap<String, Airport> airports;

    public AirportDB(){
        readAirports();
    }

    /**
     * Finds an airport with a given 3-letter code.
     * @param code the 3-letter code for the given airport
     * @return an Airport with the given code
     */
    public Airport findAirport(String code){
        return airports.get(code);
    }

    /**
     * Returns all Airports in the system.
     * @return a list of all Airports
     */
    public List<Airport> getAirports(){
        return new ArrayList<>(airports.values());
    }

    /**
     * Creates Airport classes from a series of text files: "airports.txt", "delays.txt", and "weather.txt".  Stores
     * these Airports within the class.
     */
    private void _readAirports(){
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

    /**
     * Creates Airport classes from a series of text files: "airports.txt", "delays.txt", and "weather.txt".  Stores
     * these Airports within the class.
     */
    private void readAirports() {
        airports = new HashMap<>();

        String airportCodeKey = "airportCode";
        String cityKey = "cityName";
        String timeKey = "minutes";

        CSVCoder coder = new CSVCoder();
        Map<String, Map<String, String>> airportData = coder.readMapFromFile("airports",
                new String[]{airportCodeKey, cityKey});
        Map<String, Map<String, String>> delayData = coder.readMapFromFile("delays",
                new String[]{airportCodeKey, timeKey});
        Map<String, Map<String, String>> weatherData = coder.readMapFromFile("weather",
                new String[]{airportCodeKey});

        for (Map<String, String> airportHash : airportData.values()) {
            String code = airportHash.get(airportCodeKey);
            String name = airportHash.get(cityKey);
            int delay = Integer.parseInt(delayData.get(code).get(timeKey));
            String[] weather = weatherData.get(code).get("endingValues").split(",");
            Airport airport = new Airport(code, name, delay, weather);
            airports.put(code, airport);
        }
    }
}
