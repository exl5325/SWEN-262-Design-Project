import java.util.Comparator;

/**
 * Airfare comparator class
 */
public class AirfareComparator implements Comparator<FlightInterface> {

    /**
     * Compares two flights' airfare
     * @param o1 flight 1
     * @param o2 flight 2
     * @return 1 if flight 1 > flight 2,
     *  0 if flight 1 == flight 2
     *  -1 if flight 1 < flight 2
     */
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
