package RequestResponse;

/**
 * Processes the connection request and creates the proper response
 *
 * Created by peter.audier on 11/1/2017.
 */

public class ConnectRequest implements Request{
    private int currentConnections;

    public ConnectRequest(int currentConnections){
        this.currentConnections = currentConnections;
    }

    @Override
    public Response request() {
        //check if currentConnections is equal to maximum
        return new SimpleResponse("connect");
    }
}
