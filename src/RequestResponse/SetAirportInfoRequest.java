package RequestResponse;

import Database.DBFacade;

/**
 * Processes the set airport information request and creates the proper response
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

    /*
    Checks if the server should be local or from the FAA and then tells the database how to act, then creates a
    response based on if the user asked for a legitimate server or not.
    */
    @Override
    public Response request(){
        if(server.equals("local"))
            db.disconnectAirports();
        if(server.equals("faa"))
            db.connectAirports();
        else
            return new SimpleResponse("error,unknown information server");
        return new SimpleResponse(server + ",successful");
    }
}

