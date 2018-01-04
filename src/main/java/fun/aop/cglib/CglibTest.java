package fun.aop.cglib;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

/**
 * Created by lovefly1983 on 9/4/17.
 */
public class CglibTest {
    public static void main(String[] args) {
        CglibProxy proxyA = new CglibProxy();
        B b = (B)proxyA.getProxy(B.class);
        b.bar();
    }
}

class CglibProxy implements MethodInterceptor {
    private Enhancer enhancer = new Enhancer();

    public Object getProxy(Class clazz){
        //设置需要创建子类的类
        enhancer.setSuperclass(clazz);
        enhancer.setCallback(this);
        //通过字节码技术动态创建子类实例
        return enhancer.create();
    }

    //实现MethodInterceptor接口方法
    public Object intercept(Object obj, Method method, Object[] args,
                            MethodProxy proxy) throws Throwable {
        System.out.println("前置代理");
        //通过代理类调用父类中的方法
        Object result = proxy.invokeSuper(obj, args);
        System.out.println("后置代理");
        return result;
    }
}


