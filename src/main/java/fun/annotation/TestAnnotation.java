package fun.annotation;

import java.lang.reflect.Method;

/**
 * Created by chunjiewang on 9/16/16.
 */
public class TestAnnotation {
    public static void main(String[] args) {
        try {
            Class rt_class = Class.forName("fun.annotation.Item");
            Method[] methods = rt_class.getMethods();
            boolean hasAnnotation = rt_class.isAnnotationPresent(Description.class);
            if (hasAnnotation) {
                Description description = (Description)rt_class.getAnnotation(Description.class);
                System.out.println("item has the Cacheable ----> " + description.value());
                for (Method method : methods) {
                    if (method.isAnnotationPresent(Cacheable.class)) {
                        Cacheable cacheable = (Cacheable)method.getAnnotation(Cacheable.class);
                        System.out.println("Cacheable on method -----> name: " + cacheable.name() + " value: " + cacheable.value());
                    }
                }
            }

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}
