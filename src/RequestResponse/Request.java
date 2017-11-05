package RequestResponse;

/**
 * Interface that all requests implement
 *
 * Created by peter.audier on 10/8/2017.
 */
public interface Request {

    /**
     * Processes the request and returns a response
     *
     * @return A response in proper format
     */
    public Response request();

    /**
     * Tells whether the request supports undo/redo
     *
     * @return True iff the request supports undo/redo
     */
    default public boolean supportsUndo() {
        return false;
    }

    /**
     * Undoes a request previously done and returns a response
     *
     * @return A properly formatted response
     */
    default public Response undo() {
        return null;
    }

    /**
     * Redoes a request previously undone and returns a response.
     *
     * This method typically performs the same work as request()
     * but returns an UndoRedoResponse instead of the typical response object.
     *
     * @return A peroperly formatted response
     */
    default public Response redo() {
        return null;
    }
}
