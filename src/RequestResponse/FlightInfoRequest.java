package RequestResponse;

import Database.DBManager;
import Itinerary.Itinerary;

import java.util.List;

/**
 * Processes the flight info request and creates the proper responses based on if there was an error or not.
 *
 * Created by peter.audier on 10/8/2017.
 */
public class FlightInfoRequest implements Request {
    private DBManager db;
    private String origin,destination,sortOrder;
    private int numConnections;

    public FlightInfoRequest(String origin, String destination, String sortOrder, int numConnections, DBManager db){
        this.origin = origin;
        this.destination = destination;
        this.sortOrder = sortOrder;
        this.numConnections = numConnections;
        this.db = db;
    }

    // checks if the itineraries are real and then create the proper response
    @Override
    public Response request() {
        if (db.findAirport(origin) == null)
            return new SimpleResponse("error,unknown origin");
        if (db.findAirport(destination) == null)
            return new SimpleResponse("error,unknown destination");
        if (numConnections > 2 || numConnections <0)
            return new SimpleResponse("error,invalid connection limit");
        if (!sortOrder.equals("departure") && !sortOrder.equals("arrival") && !sortOrder.equals("airfare"))
            return new SimpleResponse("error,invalid sort order");

        List<Itinerary> itineraries = db.findItineraries(origin, destination, numConnections, sortOrder);

        return new FlightInfoResponse(itineraries);
    }
}
