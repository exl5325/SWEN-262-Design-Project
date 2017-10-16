import java.util.ArrayList;
import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class FlightInfoRequest implements Request {
    private DBFacade db;
    private String origin,destination,sortOrder;
    private int numConnections;

    public FlightInfoRequest(String origin, String destination, String sortOrder, int numConnections, DBFacade db){
        this.origin = origin;
        this.destination = destination;
        this.sortOrder = sortOrder;
        this.numConnections = numConnections;
        this.db = db;
    }

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
