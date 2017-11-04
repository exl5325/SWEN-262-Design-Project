package RequestResponse;

import Itinerary.Itinerary;
import Itinerary.FlightInterface;

/**
 * Creates the response for undo and redo commands based on what the last operation was and the itinerary involved.
 *
 * Created by peter.audier on 11/2/2017.
 */

public class UndoRedoResponse implements Response {
    private String operation, passenger, reun;
    private Itinerary itinerary;

    public UndoRedoResponse(String reun, String operation, String passenger, Itinerary itinerary) {
        this.reun = reun;
        this.operation = operation;
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    @Override
    public String outputData() {
        String data = reun + "," + operation + "," + passenger + "," + itinerary.getAirfare() + "," + itinerary.getConnections();
        for(FlightInterface flight:itinerary.flights) {
            data = data + "," + flight.getFlightNumber() + "," + flight.getOrigin() + "," +
                    flight.getDepartureTime() + "," + flight.getDestination() + "," + flight.getArrivalTime();
        }
        return data;
    }
}
