/**
 * Created by peter.audier on 10/8/2017.
 */
public class FlightInfoRequest implements Request {

    private String origin,destination,sortOrder;
    private int numConnections;

    public FlightInfoRequest(){

    }

    @Override
    public Response request() {
        return null;
    }
}
