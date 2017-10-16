import java.util.List;
import java.util.Map;

/**
 * Receives text strings and parses them into requests.
 * Requests are then executed and the responses are returned.
 *
 * Created by Matthew Seaman on 10/15/2017.
 */
public class RequestParser {

    /**
     * An interface to the application's databases.
     */
    private DBFacade database;

    /**
     * A coder for reading comma-separated values.
     */
    private CSVCoder csvCoder = new CSVCoder();

    private String commandKey = "command";
    private String endingValuesKey = "endingValues";
    private String originKey = "origin";
    private String destinationKey = "destination";
    private String connectionLimitKey = "connections";
    private String sortOrderKey = "sortOrder";

    private String emptyRequestMessage = "empty-request";
    private String unknownRequestMessage = "error,unknown request";
    private String invalidConnectionLimitMessage = "error,invalid connection limit";

    public RequestParser() {
        this.database = new DBFacade();
    }

    /**
     * Processes the input and produces an output.
     *
     * @param input: The request string.
     * @return A non-empty output string.
     */
    public String process(String input) {
        try {
            Request request = createRequest(input);
            return request.request().outputData();
        } catch (Exception e) {
            return e.getMessage();
        }
    }

    /**
     * Returns a request from the input string.
     *
     * @param input: An input string representing a request.
     * @return A represented request.
     * @throws Exception: If invalid input was entered. The Exception contains the readable error message.
     */
    private Request createRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{commandKey});

        if (inputData.isEmpty()) {
            throw new Exception(emptyRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(commandKey)) {
            throw new Exception(emptyRequestMessage);
        }

        if (!inputHash.containsKey(endingValuesKey)) {
            throw new Exception(unknownRequestMessage);
        }

        switch (inputHash.get(commandKey)) {
            case "info":
                return flightInfoRequest(inputHash.get(endingValuesKey));
            case "reserve":
                return makeReservationRequest(inputHash.get(endingValuesKey));
            case "retrieve":
                return getReservationRequest(inputHash.get(endingValuesKey));
            case "delete":
                return deleteReservationRequest(inputHash.get(endingValuesKey));
            case "airport":
                return airportInfoRequest(inputHash.get(endingValuesKey));
            default:
                throw new Exception(unknownRequestMessage);
        }
    }

    private FlightInfoRequest flightInfoRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{originKey, destinationKey, connectionLimitKey, sortOrderKey});

        if (inputData.isEmpty()) {
            throw new Exception(unknownRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(originKey) || !inputHash.containsKey(destinationKey)) {
            throw new Exception(unknownRequestMessage);
        }

        if (inputHash.containsKey(sortOrderKey) && !inputHash.containsKey(connectionLimitKey)) {
            throw new Exception(unknownRequestMessage);
        }

        String origin = inputHash.get(originKey);
        String destination = inputHash.get(destinationKey);

        String connectionLimit;
        if (inputHash.containsKey(connectionLimitKey) && !inputHash.get(connectionLimitKey).isEmpty()) {
            connectionLimit = inputHash.get(connectionLimitKey);
        } else {
            connectionLimit = "2";
        }
        int numConnections;
        try {
            numConnections = Integer.parseInt(connectionLimit);
        } catch (NumberFormatException e) {
            throw new Exception(invalidConnectionLimitMessage);
        }

        String sortOrder;
        if (inputHash.containsKey(sortOrderKey)) {
            sortOrder = inputHash.get(sortOrderKey);
        } else {
            sortOrder = "departure";
        }

        return new FlightInfoRequest(origin, destination, sortOrder, numConnections, database);
    }

    private MakeReservationRequest makeReservationRequest(String input) throws Exception {

    }

    private GetReservationRequest getReservationRequest(String input) throws Exception {

    }

    private DeleteReservationRequest deleteReservationRequest(String input) throws Exception {

    }

    private AirportInfoRequest airportInfoRequest(String input) throws Exception {

    }

}
