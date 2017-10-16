package Test;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by matthewseaman on 10/16/17.
 */
public abstract class TestCase {

    public void run() {
        System.out.println("Starting " + name() + "...");

        List<Method> tests = this.testsToRun();

        for (Method test : tests) {
            run(test);
        }

        System.out.println("Completed " + tests.size() + " tests in " + name() + ".");
    }

    public void assertTrue(boolean fact, String message) {
        assertForTest(fact, message);
    }

    public void assertFalse(boolean fact, String message) {
        assertForTest(!fact, message);
    }

    public void assertNull(Object object, String message) {
        assertForTest(object == null, message);
    }

    public void assertNotNull(Object object, String message) {
        assertForTest(object != null, message);
    }

    public <T> void assertEqual(T object1, T object2, String message) {
        assertForTest(object1.equals(object2), message);
    }

    public <T> void assertNotEqual(T object1, T object2, String message) {
        assertForTest(!object1.equals(object2), message);
    }

    private void assertForTest(boolean fact, String message) {
        if (!fact) {
            fail(message);
        }
    }

    public void fail(String message) {
        System.err.println(message);
    }

    private String name() {
        return this.getClass().getSimpleName();
    }

    private List<Method> testsToRun() {
        List<Method> methodsToInvoke = new ArrayList<>();

        Method[] methods = this.getClass().getMethods();

        for (Method method : methods) {
            String name = method.getName();
            if (name.startsWith("test") && method.getParameters().length == 0) {
                methodsToInvoke.add(method);
            }
        }

        return methodsToInvoke;
    }

    private void run(Method test) {
        System.out.println("Starting test '" + test.getName() + "'...");

        try {
            long startTime = System.nanoTime();
            test.invoke(this);
            long endTime = System.nanoTime();
            double seconds = ((double)(endTime - startTime)) / 1_000_000_000.0;
            System.out.println("Completed test '" + test.getName() + "' in " + seconds + " seconds.");
        } catch (Exception e) {
            fail("Test '" + test.getName() + "' failed with message: " + e.getMessage());
        }
    }

}
