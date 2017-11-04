package RequestResponse;

import Database.DBFacade;

/**
 * Processes the connection request and creates the proper response
 *
 * Created by peter.audier on 11/2/2017.
 */

public class SetAirportInfoRequest implements Request{
    private String server;
    private DBFacade db;

    public SetAirportInfoRequest(String server, DBFacade db){
        this.server = server;
        this.db = db;
    }

    @Override
    public Response request(){
        if(db==null)//TODO method to connect to FAA or local server
            return new SimpleResponse(server+",successful");
        return new SimpleResponse("error,unknown information server");
    }
}

