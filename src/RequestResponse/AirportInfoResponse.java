package RequestResponse;

import Itinerary.Airport;

public class AirportInfoResponse implements Response{
    private Airport airport;

    AirportInfoResponse(Airport airport){
        this.airport = airport;
    }

    public String outputData(){
        String output = "airport,";
        output = FAAinfo(output);
        output = localInfo(output);
        return output;
    }

    private String FAAinfo(String s){
        return s;
    }

    private String localInfo(String s){
        s=s+airport.getName()+",";
        s=s+airport.getWeather()+",";
        s=s+airport.getDelay();
        return s;
    }
}
