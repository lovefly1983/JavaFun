package fun.annotation;

import java.lang.annotation.*;

/**
 * Created by chunjiewang on 9/16/16.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
public @interface Description {
    String value();
}
