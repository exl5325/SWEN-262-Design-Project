package Itinerary;

/**
 * Created by Eric on 10/14/2017.
 * Collection of helper functions for the comparators
 * Do NOT instantiate this class!
 */
public abstract class TimeHelper {

    /**
     * Compares times by comparing minutes
     *
     * @param time1 minutes for time 1
     * @param time2 minutes for time 2
     * @return 1 if time1 > time2, 0 if time1 == time2, -1 if time2 > time1
     */
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

    /**
     * Calculates the number of minutes since 12:00am
     * 3pm = 15 x 60 = 900
     * @param t1 time, HH:MMa/p format (ie. 3:00p)
     * @return number of minutes since 12am
     */
    public static int calculateMinutes(String[] t1){
        String minutes = t1[1].replaceAll("[^0-9.]", "");
        int result = 0;
        int hours = Integer.parseInt(t1[0]);
        char ampm = t1[1].charAt(2);
        if(ampm == 'p' && hours != 12){
            hours += 12;
        }
        else if(hours == 12 && ampm == 'a'){
            hours = 0;
        }
        result = hours * 60 + Integer.parseInt(minutes);
        return result;
    }
}
