import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by matthewseaman on 10/10/17.
 */
public class Main {

    public static void main(String[] args) {
        RequestParser parser = new RequestParser();
        Shell shell = new Shell(parser);
        shell.start();
    }

}
