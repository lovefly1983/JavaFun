package fun.spi.extensions.A;

import fun.spi.annotations.ExtType;
import fun.spi.constant.SpiConstant;
import fun.spi.extensionpoint.WorldService;

/**
 * Created by lovefly1983 on 18/12/2017.
 */
@ExtType(SpiConstant.TYPE_A)
public class WorldServiceAImpl implements WorldService {

    @Override
    public String sayWorld() {
        return "World AAAA";
    }
}
