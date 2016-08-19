package jmockit;


import jmockit.targets.Callback;
import jmockit.targets.NameCallback;
import mockit.Mock;
import mockit.MockUp;
import mockit.Mocked;
import org.junit.Assert;
import org.junit.Test;

/**
 * ®
 *
 * @author cwang
 */
public class JmockitTest {

    @Test
    public void testMocked(@Mocked Person person) {
        Assert.assertEquals(1, 1);
    }


    interface CallbackHandler {
        void handle(Callback[] callbacks);
    }



    @Test
    public void mockingAnInterface() throws Exception {
        CallbackHandler callbackHandler = new MockUp<CallbackHandler>() {
            @Mock
            void handle(Callback[] callbacks) {
                Assert.assertEquals(1, callbacks.length);
            }
        }.getMockInstance();

        callbackHandler.handle(new Callback[]{new NameCallback("Enter name:")});
    }
}


/**
 * Test only class
 */
class Person {
    private String name;
    private String address;
    private int age;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

