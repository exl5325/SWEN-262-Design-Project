package Main;

import UserInteraction.*;

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
