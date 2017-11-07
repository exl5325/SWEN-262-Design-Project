package RequestResponse;

/**
 * Processes the undo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class UndoRequest implements Request {

    private UndoManager undoManager;

    public UndoRequest(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    /**
     * Has the UndoManager undo the last thing on the stack and return an UndoRedoResponse to this
     * @return the appropriate response
     */
    @Override
    public Response request() {
        return undoManager.undo();
    }
}