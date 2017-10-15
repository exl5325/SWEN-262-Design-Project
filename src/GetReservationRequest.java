import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class GetReservationRequest implements Request {

    private String passenger, origin, destination;
    private DBFacade db;

    public GetReservationRequest(String passenger, String origin, String destination, DBFacade db){
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
        this.db = db;
    }

    @Override
    public Response request() {
        List<Reservation> reservations = db.findReservations(passenger, origin, destination);
        if(reservations.size()==0) {
            return new SimpleResponse("retrieve,0");
        }

        for(Reservation res:reservations){
            Boolean org = false, dest = false;
            for(Airport airport:db.getAirports()){
                if(airport.getName().equals(origin))
                    org = true;
                if(airport.getName().equals(destination))
                    dest = true;
            }
            if(!org)
                return new SimpleResponse("error,unknown origin");
            if(!dest)
                return new SimpleResponse("error,unknown destination");
        }

        return new GetReservationsResponse(reservations);
    }
}
