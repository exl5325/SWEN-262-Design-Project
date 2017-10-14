/**
 * Created by Eric on 10/14/2017.
 */
public abstract class TimeHelper {

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

    public static int calculateMinutes(String[] t1){
        String minutes = t1[1].replaceAll("[^0-9.]", "");
        int result = Integer.parseInt(t1[0]) * 60 + Integer.parseInt(minutes);
        if(t1[1].charAt(2) == 'p'){
            result += 720;
        }
        return result;
    }
}
