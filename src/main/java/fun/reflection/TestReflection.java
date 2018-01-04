package fun.reflection;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

/**
 * Created by lovefly1983 on 9/20/17.
 */
public class TestReflection {
    public static void main(String[] args) {
        Person p = new Person();
        Class<?> clazz = p.getClass();
//        for (Field field : clazz.getDeclaredFields()) {
//            System.out.println(field.getName());
//        }
//
//        System.out.println("All methods: --------------");
//        for (Method method : clazz.getMethods()) {
//            System.out.println(method.getName());
//        }
//
//        System.out.println("All declared methods: --------------");
//        for (Method method : clazz.getDeclaredMethods()) {
//            System.out.println(method.getName());
//        }

        Method method = null;
        try {
            method = clazz.getMethod("setName", String.class);
            method.setAccessible(true);
            method.invoke(p, "abc");

            method = clazz.getMethod("getName");
            method.setAccessible(true);
            System.out.println(method.invoke(p));

            method = clazz.getDeclaredMethod("test");
            method.setAccessible(true);
            System.out.println(method.invoke(p));
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}

class Person {
    private String name;
    private int age;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    private String test() {
        return "1111";
    }
}
