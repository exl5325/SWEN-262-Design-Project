package Test;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthewseaman on 10/16/17.
 */
public class TestCollection {

    private List<TestCase> testCases;

    public TestCollection() {
        this.testCases = new ArrayList<>();
    }

    public void run() {
        for (TestCase testCase : testCases) {
            testCase.run();
            System.out.println();
        }
    }

    public void addTestCase(TestCase testCase) {
        testCases.add(testCase);
    }
}
