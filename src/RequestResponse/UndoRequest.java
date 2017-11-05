package RequestResponse;

import RequestResponse.Request;
import RequestResponse.Response;
import RequestResponse.UndoRedoResponse;

/**
 * Processes the undo request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class UndoRequest implements Request {
    private UndoManager undoManager;

    public UndoRequest(UndoManager undoManager){
        this.undoManager = undoManager;
    }

    @Override
    public Response request() {
        return undoManager.undo();
    }
}