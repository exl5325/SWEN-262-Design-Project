package RequestResponse;

import Database.DBFacade;

/**
 * Processes the disconnect request and creates the proper response
 *
 * Created by peter.audier on 11/1/2017.
 */

public class DisconnectRequest implements Request{

    private DBFacade db;

    public DisconnectRequest(DBFacade db){
        this.db = db;
    }

    /**
     * attempts to disconnect the user
     * @return the appropriate response
     */
    @Override
    public Response request() {
        if(db.disconnect())
            return new SimpleResponse("disconnect");
        return new SimpleResponse("error,already disconnected");
    }
}
