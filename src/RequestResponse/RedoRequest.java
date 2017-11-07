package RequestResponse;

/**
 * Processes the redo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class RedoRequest implements Request{

    private UndoManager undoManager;

    public RedoRequest(UndoManager undoManager) {
        this.undoManager = undoManager;
    }

    /**
     * Has the UndoManager redo the last thing on the stack and return an UndoRedoResponse to this
     * @return the appropriate response
     */
    @Override
    public Response request() {
        return undoManager.redo();
    }
}
