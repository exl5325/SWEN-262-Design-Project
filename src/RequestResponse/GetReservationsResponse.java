package RequestResponse;

import Itinerary.Reservation;
import Itinerary.FlightInterface;
import Itinerary.Itinerary;

import java.util.List;

/**
 * Creates the response for get reservations based on a list of reservations.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class GetReservationsResponse implements Response {

    private List<Reservation> reservation;

    public GetReservationsResponse(List<Reservation> reservation){
        this.reservation = reservation;
    }

    // formats the get reservation response by iterating through all the itineraries connected to the reservations and
    // printing the information
    @Override
    public String outputData() {
        String response = "retrieve," + reservation.size();
        for(Reservation res:reservation){
            Itinerary itinerary = res.getItinerary();
            response = response + "\n";
            for(FlightInterface flight:itinerary.flights) {
                response = response + flight.getFlightNumber() + "," + flight.getOrigin() + "," +
                        flight.getDepartureTime() + "," + flight.getDestination() + "," + flight.getArrivalTime();
            }
        }
        return  response;
    }
}
