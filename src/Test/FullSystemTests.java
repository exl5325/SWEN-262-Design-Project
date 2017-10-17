package Test;

import UserInteraction.*;

/**
 * Created by matthewseaman on 10/17/17.
 */
public class FullSystemTests extends TestCase {

    private String response;

    private Shell shell;

    public FullSystemTests() {
        RequestParser parser = new RequestParser();
        this.shell = new Shell(parser);
    }

    public void testInvalidRequest() {
        send("Hello World!;");
        assertResponse("error,unknown request",
                "Did not catch unknown request."
        );
    }

    public void testPartialRequest() {
        send("airport,");
        assertResponse("partial-request",
                "Does not recognize partial request.");

        send("SFO;");
        assertTrue(
                response.startsWith("airport"),
                "Partial request did not complete."
        );
    }

    public void testAirportInfoRequest() {
        send("airport,SFO;");

        assertTrue(
                response.startsWith("airport,San Francisco"),
                "Airport Info Request did not work properly."
        );

        send("airport,BWI;");
        assertResponse("error,unknown airport",
                "Airport Info does not catch unknown airport.");
    }

    public void testFlightInfoRequest() {
        send("info,SFO,JFK;");
        assertTrue(
                response.startsWith("info,242"),
                "Flight info request does not work properly with info,SFO,JFK."
        );

        send("info,SFO,JFK,0;");
        assertResponse("info,0\n",
                "Flight info request does not work properly with info,SFO,JFK,0.");

        send("info,SFO,JFK,,airfare;");
        assertTrue(
                response.startsWith("info,242"),
                "Flight info request does not work properly with info,SFO,JFK,,airfare."
        );

        send("info,BWI,SFO;");
        assertResponse("error,unknown origin",
                "Flight info request does not catch unknown origin.");

        send("info,SFO,BWI;");
        assertResponse("error,unknown destination",
                "Flight info request does not catch unknown destination.");

        send("info,SFO,JFK,3;");
        assertResponse("error,invalid connection limit",
                "Flight info request does not catch invalid connection limit.");

        send("info,SFO,JFK,,moneys;");
        assertResponse("error,invalid sort order",
                "Flight info request does not catch invalid sort order.");
    }

    public void testReservations() {
        send("info,SFO,JFK;");
        assertTrue(
                response.startsWith("info,242"),
                "Does not work with info request."
        );

        send("reserve,1,Matt;");
        assertResponse("reserve,successful",
                "Reservation did not work.");

        send("reserve,1,Matt;");
        assertResponse("error,duplicate reservation",
                "Reservation duplicate not caught.");

        send("reserve,243,Matt;");
        assertResponse("error,invalid id",
                "Reservation invalid id not caught.");

        send("retrieve,Matt;");
        assertResponse("retrieve,1\n502,SFO,6:00a,LAS,7:37a449,LAS,4:30p,ATL,6:02p647,ATL,7:25p,JFK,10:06p",
                "Retrieving reservation failed with retrieve,Matt.");

        send("retrieve,Matt,SFO,JFK;");
        assertResponse("retrieve,1\n502,SFO,6:00a,LAS,7:37a449,LAS,4:30p,ATL,6:02p647,ATL,7:25p,JFK,10:06p",
                "Retrieving reservation failed with retrieve,Matt,SFO,JFK.");

        send("retrieve,Matt,,JFK;");
        assertResponse("retrieve,1\n502,SFO,6:00a,LAS,7:37a449,LAS,4:30p,ATL,6:02p647,ATL,7:25p,JFK,10:06p",
                "Retrieving reservation failed with retrieve,Matt,,JFK.");

        send("delete,Matt,SFO,JFK;");
        assertResponse("delete,successful",
                "Reservation not deleted.");
    }

    private void assertResponse(String responseText, String failureMessage) {
        assertTrue(response.equals(responseText), failureMessage);
    }

    private void send(String command) {
        response = shell.outputForInput(command);
    }

}
