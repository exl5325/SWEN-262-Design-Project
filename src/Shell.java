import java.util.Scanner;

/**
 * Shell is a "view" class responsible for console interaction with
 * the user. It has no knowledge of Requests and Responses themselves and deals only
 * with text.
 *
 * Created by Matt Seaman on 10/10/2017.
 */
public class Shell {

    private String input;

    private String prompt = ">>";

    private String startMessage = "Welcome to the Airline Flight Reservation Server (AFRS).";

    private Scanner scanner = new Scanner(System.in);

    public RequestResponseParser parser;

    public Shell(RequestResponseParser parser) {
        this.parser = parser;
    }

    public void sendTextInput(String input) {}

    /**
     * Starts the shell's run loop
     */
    public void start() {
        displayLine(startMessage);

        // Main run loop
        while (true) {
            displayPrompt();

            String input = receiveLine();
            if (input.equals("exit")) { break; }

            displayLine(outputForInput(input));
        }
    }

    /**
     * Scans the console and returns a user-entered line
     *
     * @return A user-entered line of text
     */
    private String receiveLine() {
        return scanner.nextLine();
    }

    /**
     * Returns the output for a given input
     *
     * @param input: The entered command text
     * @return The output for input
     */
    private String outputForInput(String input) {
        return parser.process(input);
    }

    /**
     * Prints the text of the prompt, followed by a space.
     */
    private void displayPrompt() {
        display(prompt, " ");
    }

    /**
     * Prints text without a terminator
     *
     * @param message: The text to print
     */
    private void display(String message) {
        display(message, "");
    }

    /**
     * Prints a string of text, followed by a newline terminator
     *
     * @param line: The text of the line, not including the newline terminator
     */
    private void displayLine(String line) {
        display(line, "\n");
    }

    /**
     * Prints text in the console.
     *
     * @param message: The text to print
     * @param terminator: A string to append to the end of the text.
     */
    private void display(String message, String terminator) {
        System.out.print(message);
        if (terminator != null) {
            System.out.print(terminator);
        }
    }
}
