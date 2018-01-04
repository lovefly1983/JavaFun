package spi;

import fun.spi.HelloWorldProcess;
import fun.spi.constant.SpiConstant;
import fun.spi.context.ProcessContext;
import org.junit.Test;

/**
 * Created by lovefly1983 on 18/12/2017.
 */
public class SpiTest {
    @Test
    public void checkSpi() {
        // find
        ProcessContext processContext = new ProcessContext();
        processContext.setType(SpiConstant.TYPE_A);
        HelloWorldProcess helloWorldProcess = new HelloWorldProcess(processContext);
        helloWorldProcess.execute();
    }
}
