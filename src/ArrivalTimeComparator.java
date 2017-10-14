import java.util.Comparator;

/**
 * Created by Eric on 10/9/2017.
 */
public class ArrivalTimeComparator implements Comparator<FlightInterface> {
    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        String[] t1 = o1.getArrivalTime().split(":");
        String[] t2 = o2.getArrivalTime().split(":");
        int time1 = TimeHelper.calculateMinutes(t1);
        int time2 = TimeHelper.calculateMinutes(t2);
        return TimeHelper.compareHelper(time1,time2);
    }

}
