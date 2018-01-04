package fun.spi.extensions.A;

import fun.spi.annotations.ExtType;
import fun.spi.constant.SpiConstant;
import fun.spi.extensionpoint.HelloService;

/**
 * Created by lovefly1983 on 18/12/2017.
 */
@ExtType(SpiConstant.TYPE_A)
public class HelloServiceAImpl implements HelloService {
    @Override
    public String sayHello(){
        return "hello AAA";
    }
}