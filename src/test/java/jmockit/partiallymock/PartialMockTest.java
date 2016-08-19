package jmockit.partiallymock;

import jmockit.targets.Callback;
import mockit.Expectations;
import mockit.StrictExpectations;
import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

/**
 * To play around the partial mock in jmockit.
 *
 * @author cwang
 */
public class PartialMockTest {
    /**
     * Mock with real object
     */
    @Test
    public void testPartialMock() {
        Callback callback = new Callback();
        new StrictExpectations(callback) {{
            callback.getName(); result = "abc";
        }};
        Assert.assertEquals("The getName method should be mocked", callback.getName(), "abc");
        Assert.assertEquals("The getPriority method should NOT be mocked", callback.getPriority(), "High");
    }

    static class Collaborator
    {
        final int value;

        Collaborator() { value = -1; }
        Collaborator(int value) { this.value = value; }

        int getValue() { return value; }
        final boolean simpleOperation(int a, String b, Date c) { return true; }
        static void doSomething(boolean b, String s) { throw new IllegalStateException(); }
    }

    /**
     * Mock the class and we can mock its constructor as well, it affects all the class instance.
     */
    @Test
    public void partiallyMockingAClassAndItsInstances()
    {
        final Collaborator anyInstance = new Collaborator();

        new Expectations(Collaborator.class) {{
            anyInstance.getValue(); result = 123;
        }};

        // Not mocked, as no constructor expectations were recorded:
        Collaborator c1 = new Collaborator();
        Collaborator c2 = new Collaborator(150);

        // Mocked, as a matching method expectation was recorded:
        Assert.assertEquals(123, c1.getValue());
        Assert.assertEquals(123, c2.getValue());

        // Not mocked:
        Assert.assertTrue(c1.simpleOperation(1, "b", null));
        Assert.assertEquals(45, new Collaborator(45).value);
    }

    /**
     * Mock a single instance and only this instance is being affected.
     */
    @Test
    public void partiallyMockingASingleInstance()
    {
        final Collaborator collaborator = new Collaborator(2);
        final Collaborator collaborator2 = new Collaborator(3);

        new Expectations(collaborator) {{
            collaborator.getValue(); result = 123;
            collaborator.simpleOperation(1, "", null); result = false;

            // Static methods can be dynamically mocked too.
            Collaborator.doSomething(anyBoolean, "test");
        }};

        Assert.assertEquals(3, collaborator2.getValue());

        // Mocked:
        Assert.assertEquals(123, collaborator.getValue());
        Assert.assertFalse(collaborator.simpleOperation(1, "", null));
        Collaborator.doSomething(true, "test");

        // Not mocked:
        Assert.assertEquals(2, collaborator.value);
        Assert.assertEquals(45, new Collaborator(45).getValue());
        Assert.assertEquals(-1, new Collaborator().getValue());
    }
}
