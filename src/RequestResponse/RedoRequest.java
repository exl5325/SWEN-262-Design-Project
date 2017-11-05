package RequestResponse;

/**
 * Processes the redo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class RedoRequest implements Request{
    private UndoManager undoManager;

    public RedoRequest(UndoManager undoManager){
        this.undoManager = undoManager;
    }

    @Override
    public Response request() {
        return undoManager.redo();
    }
}
