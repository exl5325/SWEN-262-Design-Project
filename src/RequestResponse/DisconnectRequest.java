package RequestResponse;

/**
 * Processes the disconnect request and creates the proper response
 *
 * Created by peter.audier on 11/1/2017.
 */

public class DisconnectRequest implements Request{

    public DisconnectRequest(){}

    @Override
    public Response request() {
        return new SimpleResponse("disconnect");
    }
}
