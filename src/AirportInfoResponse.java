/**
 * Created by peter.audier on 10/8/2017.
 */
public class AirportInfoResponse implements Response {

    private Airport airport;

    public AirportInfoResponse(Airport a){
        airport = a;
    }

    @Override
    public String outputData() {
        return "airport," + airport.getName() + "," + airport.getWeather() + "," + airport.getDelay();
    }
}
