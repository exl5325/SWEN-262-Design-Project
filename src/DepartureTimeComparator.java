import java.util.Comparator;

/**
 * Departure time comparison class
 */
public class DepartureTimeComparator implements Comparator<FlightInterface> {
    /**
     * Compares arrival departure for flight interfaces
     * @param o1 the first flight
     * @param o2 the second flight
     * @return 1 if flight 1 is later than flight 2
     * 0 if equal
     * -1 if flight 2 is later than flight 1
     */
    @Override
    public int compare(FlightInterface o1, FlightInterface o2) {
        String[] t1 = o1.getDepartureTime().split(":");
        String[] t2 = o2.getDepartureTime().split(":");
        int time1 = TimeHelper.calculateMinutes(t1);
        int time2 = TimeHelper.calculateMinutes(t2);
        return TimeHelper.compareHelper(time1,time2);
    }
}
