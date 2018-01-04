package fun.spi.extensions.B;

import fun.spi.annotations.ExtType;
import fun.spi.constant.SpiConstant;
import fun.spi.extensionpoint.HelloService;

/**
 * Created by lovefly1983 on 18/12/2017.
 */
@ExtType(SpiConstant.TYPE_B)
public class HelloServiceBImpl implements HelloService {
    @Override
    public String sayHello(){
        return "hello BBB";
    }
}