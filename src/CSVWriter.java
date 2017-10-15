import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

/**
 * Class of functions to write to CSV file
 */
public class CSVWriter {

    /**
     * Overwrites entire file and rewrite reservations after a delete request is made.
     * @param lr list of reservations
     * @throws IOException if file is not found
     */
    public static void writeReservationAfterDelete(List<Reservation> lr) throws IOException {
        String filename = "reservations.csv";

        File reserv = new File(filename);
        FileOutputStream reservStream = new FileOutputStream(reserv, false);
        for(Reservation r: lr){
            reservStream.write(writer(r));
        }
    }

    /**
     * Creates byte array to write to CSV
     * @param r The reservation
     * @return byte array
     */
    private static byte[] writer(Reservation r){
        Itinerary i = r.getItinerary();
        String result = "";
        String name = "";
        int price = 0;
        int flightNumber = 0;
        String originAirport = "";
        String destinationAirport = "";
        String originTime = "";
        String destinationTime = "";
        name = r.getPassenger();
        price = i.getAirfare();
        //flightNumber = i.getID(); //todo
        originAirport = i.getOrigin();
        destinationAirport = i.getDestination();
        originTime = i.getDepartureTime();
        destinationTime = i.getArrivalTime();
        result = name + "," + price + "," + flightNumber + "," + originAirport + "," +
                destinationAirport + "," + originTime + "," + destinationTime;
        result += "\n";
        return result.getBytes();
    }

    /**
     * Write function to write a new reservation to CSV after one was created
     * @param r the reservation just created
     * @throws IOException
     */
    public static void writeAfterAdd(Reservation r) throws IOException {
        String filename = "reservations.csv";
        File reserv = new File(filename);
        FileOutputStream reservStream = new FileOutputStream(reserv, true);
        reservStream.write(writer(r));
    }
}
