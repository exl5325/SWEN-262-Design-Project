package UserInteraction;

/**
 * Shell is a "view" class responsible for console interaction with
 * the user. It has no knowledge of Requests and Responses themselves and deals only
 * with text.
 *
 * This class has been modified so that it can interact with the system's GUI
 *
 * Created by Matt Seaman on 10/10/2017.
 * Modified by Eric Lin on 11/4/2017
 */
public class ShellGUI {

    public RequestParser parser;

    public ShellGUI(RequestParser parser) {
        this.parser = parser;
    }

    /**
     * Returns the output for a given input
     *
     * @param input: The entered command text
     * @return The output for input
     */
    public String outputForInput(String input) {
        return parser.process(input);
    }

    /**
     * Prints a string of text, followed by a newline terminator
     *
     * @param line: The text of the line, not including the newline terminator
     */
    public String displayLine(String line) {
        return display(line, "\n");
    }

    /**
     * Prints text in the console.
     *
     * @param message: The text to print
     * @param terminator: A string to append to the end of the text.
     */
    public String display(String message, String terminator) {
        return message += terminator;

    }
}
