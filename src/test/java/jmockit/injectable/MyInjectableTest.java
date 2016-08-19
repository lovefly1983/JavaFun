package jmockit.injectable;

import jmockit.targets.Callback;
import jmockit.targets.NameCallback;
import jmockit.targets.PlaceCallback;
import mockit.Injectable;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;


/**
 * @author cwang
 */
public class MyInjectableTest {
    // Mocked instances (rather than conventional "mock objects") will be
    // automatically created and assigned to annotated mock fields:
    @Mocked
    NameCallback mock1; // all current and future instances are mocked

    @Injectable
    Callback anotherMock; // only one particular instance is mocked

    @Test
    public void myFirstTestMethod() {
    }

    /**
     * Set on the method level will only affect the method itself.
     * @param testSpecificMock
     */
    @Test
    public void testMethodWithMockParameter(@Mocked PlaceCallback testSpecificMock) {
        Assert.assertNull(testSpecificMock.getName());
    }

    @Test
    public void testMocked() {
        NameCallback a = new NameCallback("name");
        Assert.assertNull("@Mocked annotation make all the instance mocked...", a.getName());
    }

    @Test
    public void testInjectable() {
        Callback a = new Callback("name");
        Assert.assertNotNull("@Injectable only has effect on the instance: anotherMock", a.getName());
        Assert.assertNull("Only the injectable one is mocked", anotherMock.getName());
    }
}
