package RequestResponse;

import Database.DBFacade;

/**
 * Processes the connection request and creates the proper response
 *
 * Created by peter.audier on 11/1/2017.
 */

public class ConnectRequest implements Request{
    private DBFacade db;

    public ConnectRequest(DBFacade db){
        this.db = db;
    }

    /**
     * attempts to connect the user
     * @return the appropriate response
     */
    @Override
    public Response request() {
        if(db.connect())
            return new SimpleResponse("connect");
        return new SimpleResponse("error,already connected");
    }
}
