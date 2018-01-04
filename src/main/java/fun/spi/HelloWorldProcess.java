package fun.spi;

import com.beust.jcommander.internal.Lists;
import fun.spi.context.ProcessContext;
import fun.spi.extensionpoint.HelloService;
import fun.spi.extensionpoint.WorldService;
import fun.spi.factory.ServiceProviderFactory;

import java.util.List;

/**
 * Created by lovefly1983 on 18/12/2017.
 */
public class HelloWorldProcess {
    List<HelloService> helloServices = Lists.newArrayList();
    List<WorldService> worldServices = Lists.newArrayList();

    public HelloWorldProcess(ProcessContext processContext) {
        helloServices = ServiceProviderFactory.getService(HelloService.class, processContext.getType());
        worldServices = ServiceProviderFactory.getService(WorldService.class, processContext.getType());
    }

    public void execute() {
        // point 1
        hello();
        // do whatever
        // point 2
        world();
    }

    private void hello() {
        for (HelloService helloService : helloServices) {
            performAction(helloService.sayHello());
        }
    }
    private void world() {
        for (WorldService worldService : worldServices) {
            performAction(worldService.sayWorld());
        }
    }

    private void performAction(String input) {
        System.out.println(input);
    }
}
