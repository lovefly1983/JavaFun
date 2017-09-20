package fun.aop.jdkproxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * Created by chunjiewang on 9/3/17.
 */
public class JdkProxyTest {
    public static void main(String[] agrs) {
        IA a = (IA) Proxy.newProxyInstance(IA.class.getClassLoader(), new Class[]{IA.class},
                new AIH(new A()));
        a.foo();
    }

    /**
     * Created by qingyun on 9/4/17.
     */
    interface IA {
        void foo();
    }

    /**
     * Created by qingyun on 9/4/17.
     */
    static class A implements IA {
        @Override
        public void foo() {
            System.out.println("foo operation");
        }
    }
}

class AIH implements InvocationHandler {
    private Object target;

    public AIH(Object target) {
        this.target = target;
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        System.out.println("前置代理");
        Object ret = method.invoke(target, args);
        System.out.println("后置代理");
        return ret;
    }
}


