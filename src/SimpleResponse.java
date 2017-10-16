/**
 * Response that prints out the String that is passed to it. Used for error responses and the more simple responses.
 *
 * Created by peter.audier on 10/14/2017.
 */
public class SimpleResponse implements Response {

    private String response;

    public SimpleResponse(String res){
        response = res;
    }

    @Override
    public String outputData(){
        return response;
    }
}
