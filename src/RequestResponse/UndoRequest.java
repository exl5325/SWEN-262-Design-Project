package RequestResponse;

import Itinerary.Itinerary;
import RequestResponse.Request;
import RequestResponse.Response;
import RequestResponse.UndoRedoResponse;

/**
 * Processes the redo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class UndoRequest implements Request {
    private String operation, passenger;
    private Itinerary itinerary;

    public UndoRequest(String operation, String passenger, Itinerary itinerary){
        this.operation = operation;
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    @Override
    public Response request() {
        return new UndoRedoResponse("undo",operation,passenger,itinerary);
    }
}