import java.util.List;

/**
 * Created by peter.audier on 10/8/2017.
 */
public class GetReservationRequest implements Request {

    private String passenger, origin, destination;
    private ReservationDB db;
    private AirportDB airports;

    public GetReservationRequest(String passenger, String origin, String destination, ReservationDB db, AirportDB airports){
        this.passenger = passenger;
        this.origin = origin;
        this.destination = destination;
        this.db = db;
        this.airports = airports;
    }

    @Override
    public Response request() {
        List<Reservation> reservations = db.findReservations(passenger, origin, destination);
        if(reservations.size()>1) {
            //TODO WE WILL HAVE A PRETTY BIG PROBLEM IF THIS HAPPENS
        }
        Reservation reservation = reservations.get(0);
        Boolean org = false, dest = false;
        for(Airport airport:airports.getAirports()){
            if(airport.getName().equals(origin))
                org = true;
            if(airport.getName().equals(destination))
                dest = true;
        }
        if(!org)
            return new SimpleResponse("error,unknown origin");
        if(!dest)
            return new SimpleResponse("error,unknown destination");
        return new GetReservationsResponse(reservation);
    }
}
