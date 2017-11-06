package RequestResponse;

/**
 * Processes the undo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class UndoRequest implements Request {

    public UndoRequest() {}

    /**
     * Has the UndoManager undo the last thing on the stack and return an UndoRedoResponse to this
     * @return the appropriate response
     */
    @Override
    public Response request() {
        return UndoManager.shared.undo();
    }
}