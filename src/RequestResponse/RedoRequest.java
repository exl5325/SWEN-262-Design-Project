package RequestResponse;

import Itinerary.Itinerary;

/**
 * Processes the redo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class RedoRequest implements Request{
    private String operation, passenger;
    private Itinerary itinerary;

    public RedoRequest(String operation, String passenger, Itinerary itinerary){
        this.operation = operation;
        this.passenger = passenger;
        this.itinerary = itinerary;
    }

    @Override
    public Response request() {
        //TODO
        return new UndoRedoResponse("redo",operation,passenger,itinerary);
    }
}
