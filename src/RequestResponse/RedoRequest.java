package RequestResponse;

/**
 * Processes the redo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class RedoRequest implements Request{

    public RedoRequest() {}

    /**
     * Has the UndoManager redo the last thing on the stack and return an UndoRedoResponse to this
     * @return the appropriate response
     */
    @Override
    public Response request() {
        return UndoManager.shared.redo();
    }
}
