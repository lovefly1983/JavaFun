package jmockit.mocked;

import jmockit.targets.NameCallback;
import mockit.Expectations;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

/**
 * @author cwang
 */
public class MockedTest {

    @Mocked NameCallback mockedNameCallback;

    /**
     * Set on the method level will only affect the method itself.
     * @param testSpecificMock
     */
    @Test
    public void testMethodWithMockParameter(@Mocked NameCallback testSpecificMock) {
        new Expectations() {{
            testSpecificMock.getName(); result = "abc";
        }};
        Assert.assertEquals("abc", testSpecificMock.getName());
    }

    @Test
    public void testMocked() {
        NameCallback a = new NameCallback("name");
        Assert.assertNull("@Mocked annotation make all the instance mocked...", a.getName());
    }

}
