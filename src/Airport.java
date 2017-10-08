import java.util.List;

/**
 * Created by calvinclark on 10/8/17.
 */
public class Airport {
    private String airportCode;
    private int delayTime;
    private List<String> weather;
    private int weatherIndex = 0;

    public Airport(String airportCode, int delayTime, List<String> weather) {
        this.airportCode = airportCode;
        this.delayTime = delayTime;
        this.weather = weather;
    }

    public String getAirportCode(){
        return airportCode;
    }

    public String getWeather() {
        String currentWeather = weather.get(weatherIndex);
        weatherIndex = (weatherIndex+1)%weather.size();
        return currentWeather;
    }
}
