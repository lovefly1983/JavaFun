package jmockit.mockup;

import jmockit.targets.Callback;
import jmockit.targets.CallbackHandler;
import jmockit.targets.NameCallback;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author cwang
 */
public class MockupTest {
    private static final String MOCKED_NAME = "mocked name";

    @Test
    public void testMockup() {
        new MockUp<Callback>() {
            @SuppressWarnings("warnning")
            @Mock
            public String getName() {
                return MOCKED_NAME;
            }
        };
        Callback callback = new Callback();
        Assert.assertEquals("", MOCKED_NAME, callback.getName());
    }


    @Test
    public void fakingAnInterface() throws Exception
    {
        CallbackHandler callbackHandler = new MockUp<CallbackHandler>() {
            @Mock
            void handle(Callback[] callbacks)
            {
                System.out.println("Mock the interface ....");
            }
        }.getMockInstance();

        callbackHandler.handle(new Callback[] {new NameCallback("Enter name:")});
    }
}
