/**
 * Holds info and calculates weather for a specific airport.
 *
 * Created by calvinclark on 10/8/17.
 */
public class Airport {
    private String airportCode;
    private String name;
    private int delayTime;
    private String[] weather;
    private int weatherIndex = 0;

    /**
     * Creates a new Airport class.
     *
     * @param airportCode the 3-letter code for the airport
     * @param name the full name of the airport
     * @param delayTime the minimum time that must be spent at the airport
     * @param weather All possible
     */
    public Airport(String airportCode, String name, int delayTime, String[] weather) {
        this.airportCode = airportCode;
        this.delayTime = delayTime;
        this.weather = weather;
        this.name = name;
    }

    /**
     * Returns the 3-letter code for the airport
     *
     * @return the airport code
     */
    public String getAirportCode(){
        return airportCode;
    }

    /**
     * Returns the full name of the airport
     *
     * @return the airport name
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the minimum amount of time a passenger must spend at the airport.
     * 
     * @return the minimum delay time
     */
    public int getDelay() {
        return delayTime;
    }

    /**
     * Returns the current weather.  Increments weather to return the next group of weather and temperature.
     *
     * @return current formatted weather string
     */
    public String getWeather() {
        String currentWeather = weather[weatherIndex] + "," + weather[weatherIndex+1];
        weatherIndex = (weatherIndex+2)%weather.length;
        return currentWeather;
    }
}
