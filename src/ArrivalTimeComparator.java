import java.util.Comparator;

/**
 * Created by Eric on 10/9/2017.
 */
public class ArrivalTimeComparator implements Comparator<FlightInterface> {
    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        int time1 = timeHelper(o1);
        int time2 = timeHelper(o2);
        return compareHelper(time1, time2);

    }

    public static int compareHelper(int time1, int time2){
        if(time1 > time2){
            return 1;
        }
        else if(time1 < time2){
            return -1;
        }
        else {
            return 0;
        }
    }

    private int timeHelper(FlightInterface o1){
        String[] t1 = o1.getArrivalTime().split(":");
        String minutes = t1[1].replaceAll("[^0-9.]", "");
        int result = Integer.parseInt(t1[0]) * 60 + Integer.parseInt(minutes);
        if(t1[1].charAt(2) == 'p'){
            result += 720;
        }
        return result;
    }
}
