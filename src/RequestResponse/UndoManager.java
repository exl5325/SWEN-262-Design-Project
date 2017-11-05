package RequestResponse;

import java.util.ArrayDeque;
import java.util.Deque;

/**
 * Manages the undo and redo stack for requests.
 */
public class UndoManager {

    /**
     * A singleton instance of UndoManager for the system
     */
    public static UndoManager shared = new UndoManager();

    private Deque<Request> undoStack;

    private Deque<Request> redoStack;

    /**
     * Creates a new UndoManager with its own undo and redo stacks
     */
    public UndoManager() {
        this.undoStack = new ArrayDeque<>();
        this.redoStack = new ArrayDeque<>();
    }

    /**
     * Indicates that the given request has been or will be executed and should be added to the stack.
     * This also clears the redo stack.
     *
     * Note: This method does nothing if the request does not support undo.
     */
    public void addRequest(Request request) {
        if (request.supportsUndo()) {
            this.undoStack.push(request);
            this.redoStack.clear();
        }
    }

    /**
     * Undoes the operation at the top of the undo stack and forwards the response.
     *
     * The undone request will be added to the redo stack.
     *
     * @return A valid response
     */
    public Response undo() {
        if (undoStack.isEmpty()) {
            return new SimpleResponse("error,no request available");
        }

        Request request = undoStack.pop();
        redoStack.push(request);
        return request.request();
    }

    /**
     * Redoes the operation at the top of the redo stack and forwards the response.
     *
     * The redone request will be added to the undo stack.
     *
     * @return A valid response
     */
    public Response redo() {
        if (redoStack.isEmpty()) {
            return new SimpleResponse("error,no request available");
        }

        Request request = redoStack.pop();
        undoStack.push(request);
        return request.request();
    }

}
