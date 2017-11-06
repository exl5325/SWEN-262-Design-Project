package RequestResponse;

import Database.DBManager;
import Itinerary.Itinerary;
import Itinerary.Reservation;

import java.util.List;

/**
 * Processes the delete reservation request and creates the proper responses based on if there was an error or not.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class DeleteReservationRequest implements Request {

    private String passenger, origin, destination;
    private DBManager db;

    // will hold itinerary associated with reservation that was deleted
    private Itinerary itinerary;

    public DeleteReservationRequest(String passenger, String origin, String destination, DBManager db){
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
        this.db = db;
    }

    // attempts to delete the reservation and returns a response based on if it was successful or not
    @Override
    public Response request() {
        List<Reservation> reservations = db.findReservations(passenger, origin, destination);
        if(db.deleteReservation(passenger, origin, destination)) {
            UndoManager.shared.addRequest(this);
            itinerary = reservations.get(0).getItinerary();
            return new SimpleResponse("delete,successful");
        }
        return new SimpleResponse("error,reservation not found");
    }

    @Override
    public boolean supportsUndo() {
        return true;
    }

    @Override
    public Response undo() {
        db.createReservation(passenger, itinerary);
        return new UndoRedoResponse("undo", "delete", passenger, itinerary);
    }

    @Override
    public Response redo() {
        db.deleteReservation(passenger, origin, destination);
        return new UndoRedoResponse("redo", "delete", passenger, itinerary);
    }
}
