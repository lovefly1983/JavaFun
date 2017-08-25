package fun.annotation;

import java.lang.annotation.*;

/**
 * Created by chunjiewang on 9/16/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface Cacheable {
    String name();
    boolean value() default false;
}
