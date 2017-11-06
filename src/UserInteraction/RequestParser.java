package UserInteraction;

import Database.DBManager;
import Database.CSVCoder;
import RequestResponse.*;

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
    private DBManager database;

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
    private String idKey = "id";
    private String passengerKey = "passenger";
    private String airportKey = "airport";
    private String infoServerKey = "info-server";

    private String emptyRequestMessage = "empty-request";
    private String unknownRequestMessage = "error,unknown request";
    private String invalidConnectionLimitMessage = "error,invalid connection limit";
    private String invalidIdMessage = "error,invalid id";
    private String partialRequestMessage = "partial-request";
    private String getInvalidConnectionMessage = "error,invalid connection";

    private String partialRequest = null;

    public RequestParser() {
        this.database = new DBManager();
    }

    /**
     * Processes the input and produces an output.
     *
     * @param input: The request string.
     * @return A non-empty output string.
     */
    public String process(String input) {
        input = input.trim();

        if (input.isEmpty()) {
            return emptyRequestMessage;
        }

        if (!input.endsWith(";")) {
            partialRequest = partialRequest == null ? input : partialRequest + input;
            return partialRequestMessage;
        } else {
            input = input.substring(0, input.length() - 1);
            input = partialRequest == null ? input : partialRequest + input;
            partialRequest = null;
        }

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

        switch (inputHash.get(commandKey)) {
            case "connect":
                return connectRequest();
            case "disconnect":
                return disconnectRequest();
            default: break;
        }

        if (!database.isConnected()) {
            throw new Exception(getInvalidConnectionMessage);
        }

        switch (inputHash.get(commandKey)) {
            case "undo":
                return undoRequest();
            case "redo":
                return redoRequest();
            default: break;
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
            case "server":
                return setAirportInfoRequest(inputHash.get(endingValuesKey));
            default:
                throw new Exception(unknownRequestMessage);
        }
    }

    /**
     * Returns a RequestResponse.FlightInfoRequest for the given request text.
     *
     * @param input: An input string not including the command keyword.
     * @return A RequestResponse.FlightInfoRequest for the given request text.
     * @throws Exception: For invalid request text.
     */
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

    /**
     * Returns a MakeReservationRequest for the given request text.
     *
     * @param input: An input string not including the command keyword.
     * @return A MakeReservationRequest for the given request text.
     * @throws Exception: For invalid request text.
     */
    private MakeReservationRequest makeReservationRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{idKey, passengerKey});

        if (inputData.isEmpty()) {
            throw new Exception(unknownRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(idKey) || !inputHash.containsKey(passengerKey)) {
            throw new Exception(unknownRequestMessage);
        }

        String passenger = inputHash.get(passengerKey);
        String idStr = inputHash.get(idKey);
        int id;
        try {
            id = Integer.parseInt(idStr);
        } catch (NumberFormatException e) {
            throw new Exception(invalidIdMessage);
        }

        return new MakeReservationRequest(passenger, id, database);
    }

    private GetReservationRequest getReservationRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{passengerKey, originKey, destinationKey});

        if (inputData.isEmpty()) {
            throw new Exception(unknownRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(passengerKey)) {
            throw new Exception(unknownRequestMessage);
        }

        if (inputHash.containsKey(destinationKey) && !inputHash.containsKey(originKey)) {
            throw new Exception(unknownRequestMessage);
        }

        String passenger = inputHash.get(passengerKey);

        String origin;
        if (inputHash.containsKey(originKey)) {
            origin = inputHash.get(originKey);
        } else {
            origin = "";
        }

        String destination;
        if (inputHash.containsKey(destinationKey)) {
            destination = inputHash.get(destinationKey);
        } else {
            destination = "";
        }

        return new GetReservationRequest(passenger, origin, destination, database);
    }

    private DeleteReservationRequest deleteReservationRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{passengerKey, originKey, destinationKey});

        if (inputData.isEmpty()) {
            throw new Exception(unknownRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(passengerKey) || !inputHash.containsKey(originKey)
                || !inputHash.containsKey(destinationKey)) {
            throw new Exception(unknownRequestMessage);
        }

        String passenger = inputHash.get(passengerKey);
        String origin = inputHash.get(originKey);
        String destination = inputHash.get(destinationKey);

        return new DeleteReservationRequest(passenger, origin, destination, database);
    }

    private AirportInfoRequest airportInfoRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{airportKey});

        if (inputData.isEmpty()) {
            throw new Exception(unknownRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(airportKey)) {
            throw new Exception(unknownRequestMessage);
        }

        String airport = inputHash.get(airportKey);

        return new AirportInfoRequest(airport, database);
    }

    private ConnectRequest connectRequest() {
        return new ConnectRequest(database);
    }

    private DisconnectRequest disconnectRequest() {
        return new DisconnectRequest(database);
    }

    private UndoRequest undoRequest() {
        return new UndoRequest();
    }

    private RedoRequest redoRequest() {
        return new RedoRequest();
    }

    private SetAirportInfoRequest setAirportInfoRequest(String input) throws Exception {
        List<Map<String, String>> inputData = csvCoder.readListFromString(input,
                new String[]{infoServerKey});

        if (inputData.isEmpty()) {
            throw new Exception(unknownRequestMessage);
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(infoServerKey)) {
            throw new Exception(unknownRequestMessage);
        }

        String infoServer = inputHash.get(infoServerKey);

        return new SetAirportInfoRequest(infoServer, database);
    }

}
