package RequestResponse;

import Database.DBManager;
import Itinerary.Itinerary;

/**
 * Processes the make reservation request and creates the proper responses based on if there was an error or not.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class MakeReservationRequest implements Request {

    private DBManager db;
    private UndoManager undoManager;

    private String passenger;
    private int id;
    private Itinerary itinerary;

    public MakeReservationRequest(String passenger, int id, DBManager db, UndoManager undoManager){
        this.passenger = passenger;
        this.id = id;
        this.db = db;
        this.undoManager = undoManager;
    }

    // checks if the reservation was duplicated or if the id is invalid. Creates a response accordingly.
    @Override
    public Response request() {
        if (id <= 0 || db.numberOfSavedItineraries() < id) {
            return new SimpleResponse("error,invalid id");
        }
        if(db.createReservation(passenger,id)) {
            undoManager.addRequest(this);
            itinerary = db.savedItineraryWithId(id);
            return new SimpleResponse("reserve,successful");
        }
        return new SimpleResponse("error,duplicate reservation");
    }

    @Override
    public boolean supportsUndo() {
        return true;
    }

    @Override
    public Response undo() {
        db.deleteReservation(passenger, itinerary.getOrigin(), itinerary.getDestination());
        return new UndoRedoResponse("undo", "reserve", passenger, itinerary);
    }

    @Override
    public Response redo() {
        db.createReservation(passenger, itinerary);
        return new UndoRedoResponse("redo", "reserve", passenger, itinerary);
    }

}
