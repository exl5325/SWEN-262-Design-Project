package Test;

/**
 * Created by matthewseaman on 10/16/17.
 */
public class TestMain {

    public static void main(String[] args) {
        TestCollection tests = new TestCollection();

        tests.addTestCase(new ItineraryTests());

        tests.run();
    }

}
