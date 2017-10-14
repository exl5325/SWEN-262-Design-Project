import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class Airport {
    private String airportCode;
    private String name;
    private int delayTime;
    private String[] weather;
    private int weatherIndex = 0;

    public Airport(String airportCode, String name, int delayTime, String[] weather) {
        this.airportCode = airportCode;
        this.delayTime = delayTime;
        this.weather = weather;
    }

    public String getAirportCode(){
        return airportCode;
    }
    public String getName() { return name; }
    public int getDelay() { return delayTime; }

    public String getWeather() {
        String currentWeather = weather[weatherIndex];
        weatherIndex = (weatherIndex+1)%weather.length;
        return currentWeather;
    }
}
