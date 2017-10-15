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
        List<Itinerary> itineraries = db.findItineraries(origin, destination, numConnections, sortOrder);
        for(Itinerary itinerary:itineraries) {
            if (itinerary.getOrigin() == null)
                return new SimpleResponse("error,unknown origin");
            if (itinerary.getDestination() == null)
                return new SimpleResponse("error,unknown destination");
            if (itinerary.getConnections() > 2 || itinerary.getConnections() < 0)
                return new SimpleResponse("error,invalid connection limit");
            if (itinerary.getDestination() == null)
                return new SimpleResponse("error,unknown destination");
            if ()
            //TODO add for if sorting method is invalid (error,invalid sort order)
        }

        return new FlightInfoResponse(itineraries);
    }
}
