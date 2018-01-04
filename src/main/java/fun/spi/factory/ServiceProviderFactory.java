package fun.spi.factory;

import com.beust.jcommander.internal.Lists;
import fun.spi.annotations.ExtType;

import java.util.List;
import java.util.Objects;
import java.util.ServiceLoader;

/**
 * Created by lovefly1983 on 18/12/2017.
 */
public class ServiceProviderFactory {
    public static <T> List<T> getService(Class<T> clazz, String type) {
        ServiceLoader<T> demoServiceServiceLoader = ServiceLoader.load(clazz);
        List<T> ret = Lists.newArrayList();
        for (T demoService : demoServiceServiceLoader) {
            ExtType extType = demoService.getClass().getAnnotation(ExtType.class);
            if (extType != null && Objects.equals(extType.value(), type)) {
                ret.add(demoService);
            }
        }
        return ret;
    }
}
