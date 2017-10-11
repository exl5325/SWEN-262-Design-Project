import java.util.Comparator;

/**
 * Created by Eric on 10/9/2017.
 */
public class AirfareComparator implements Comparator<FlightInterface> {

    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        if(o1.getAirfare() > o2.getAirfare()){
            return 1;
        }
        else if(o1.getAirfare() < o2.getAirfare()){
            return -1;
        }else {
            return 0;
        }
    }
}
