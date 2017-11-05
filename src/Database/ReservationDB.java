package Database;

import Itinerary.*;

import java.util.*;

/**
 * Holds, deletes, and searches reservations contained in the system.
 *
 * Created by calvinclark on 10/8/17.
 */
public class ReservationDB {
    public static ReservationDB shared = new ReservationDB();

    private String storageFilename = "data/reservations";
    private List<Reservation> reservations;
    public ReservationDB() {
        readReservations();
    }

    /**
     * Creates a new reservation with a given passenger name and itinerary.
     *
     * @param passenger the name of the passenger making the reservation
     * @param itinerary the itinerary the reservation is being made for
     * @return a boolean indicating if the reservation is created properly
     */
    public boolean createReservation(String passenger, Itinerary itinerary){
        for(Reservation r : reservations){
            if(r.sameReservation(passenger,itinerary.getOrigin(),itinerary.getDestination())){
                return false;
            }
        }
        Reservation newReservation = new Reservation(passenger, itinerary);
        reservations.add(newReservation);
        saveReservations();
        return true;
    }

    /**
     * Deletes a reservation with a given passenger name, origin airport, and destination airport.
     *
     * @param passenger the passenger name on the reservation to be deleted
     * @param origin the origin airport code on the reservation to be deleted
     * @param destination the destination airport code on the reservation to be deleted
     * @return a boolean indicating that the reservation was properly deleted
     */
    public boolean deleteReservation(String passenger, String origin, String destination) {
        for(int i = 0; i < reservations.size(); i++) {
            Reservation r = reservations.get(i);
            if(r.sameReservation(passenger, origin, destination)){
                reservations.remove(i);
                saveReservations();
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for a reservation with a given passenger name, origin airport, and destination airport.
     *
     * Origin and/or destination may be empty strings to ignore filtering by that value.
     *
     * @param passenger the passenger name on the reservation to search for
     * @param origin the origin airport code on the reservation to search for
     * @param destination the destination airport code on the reservation to search for
     * @return a list of all reservations that match the given values
     */
    public List<Reservation> findReservations(String passenger, String origin, String destination){
        List<Reservation> found = new ArrayList<>();
        for(Reservation r: reservations){
            if (!r.getPassenger().equals(passenger)) { continue; }
            if (!origin.isEmpty() && !r.getOrigin().equals(origin)) { continue; }
            if (!destination.isEmpty() && !r.getDestination().equals(destination)) { continue; }
            found.add(r);
        }
        return found;
    }

    /**
     * Reads in a CSV file named "reservations.txt" and sets reservations on the class.
     */
    private void readReservations() {
        reservations = new ArrayList<>();

        String passengerKey = "passenger";
        String originKey = "origin";
        String destinationKey = "destination";
        String departureKey = "departure";
        String arrivalKey = "arrival";
        String flightNumberKey = "flightNumber";
        String airfareKey = "airfare";

        CSVCoder coder = new CSVCoder();
        List<Map<String, String>> reservationData = coder.readListFromFile(storageFilename,
                new String[]{passengerKey});

        for (Map<String, String> reservationHash : reservationData) {
            // Passenger
            String passenger = reservationHash.get(passengerKey);

            // Flights
            String flightsString = reservationHash.get("endingValues");
            String[] flightStrings = partitionFlightsString(flightsString);
            List<FlightInterface> flights = new ArrayList<>();
            for (String flightString : flightStrings) {
                Map<String, String> flightHash = coder.readListFromString(flightString,
                        new String[]{originKey, destinationKey, departureKey, arrivalKey, flightNumberKey, airfareKey})
                        .get(0);
                Flight flight = new Flight(flightHash.get(flightNumberKey),
                        Integer.parseInt(flightHash.get(airfareKey)),
                        flightHash.get(destinationKey),
                        flightHash.get(originKey),
                        flightHash.get(arrivalKey),
                        flightHash.get(departureKey));
                flights.add(flight);
            }

            Itinerary itinerary = new Itinerary(flights);
            Reservation reservation = new Reservation(passenger, itinerary);
            reservations.add(reservation);
        }
    }

    /**
     * Partitions flightsString into csv string pieces: one for each flight
     *
     * @param flightsString: A CSV string of all flights for the reservation
     * @return An array holding each flight as a CSV string
     */
    private String[] partitionFlightsString(String flightsString) {
        String[] values = flightsString.split(",");
        int newLength = values.length / 6;
        String[] flights = new String[newLength];
        for (int i = 0; i < newLength; i++) {
            String[] pieces = Arrays.copyOfRange(values, i * 6, i * 6 + 6);
            flights[i] = String.join(",", pieces);
        }
        return flights;
    }

    /**
     * Saves reservations persistently to disk in reservations.txt.
     *
     * The format of each line is:
     * passengerName,flights
     * where flights is a list of flights, each of the form:
     * origin,destination,departure,arrival,flightNumber,airfare
     */
    private void saveReservations() {
        List<List<String>> reservationData = new ArrayList<>();

        for (Reservation reservation : reservations) {
            List<String> reservationValues = new ArrayList<>();

            // Passenger
            reservationValues.add(reservation.getPassenger());

            // Flights
            for (FlightInterface flight : reservation.getItinerary().flights) {
                reservationValues.add(flight.getOrigin());
                reservationValues.add(flight.getDestination());
                reservationValues.add(flight.getDepartureTime());
                reservationValues.add(flight.getArrivalTime());
                reservationValues.add(flight.getFlightNumber());
                reservationValues.add(flight.getAirfare() + "");
            }

            reservationData.add(reservationValues);
        }

        CSVCoder coder = new CSVCoder();
        coder.writeToFile(reservationData, storageFilename);
    }

}
