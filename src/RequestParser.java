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
     * A coder for reading comma-separated values.
     */
    CSVCoder csvCoder = new CSVCoder();

    String commandKey = "command";

    String endingValuesKey = "endingValues";

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
            throw new Exception("empty-request");
        }

        Map<String, String> inputHash = inputData.get(0);

        if (!inputHash.containsKey(commandKey)) {
            throw new Exception("empty-request");
        }

        if (!inputHash.containsKey(endingValuesKey)) {
            throw new Exception("error,unknown request");
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
                throw new Exception("error,unknown request");
        }
    }

    private FlightInfoRequest flightInfoRequest(String input) throws Exception {

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
