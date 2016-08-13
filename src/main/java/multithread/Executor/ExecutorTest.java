package multithread.Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lovefly1983.
 */
public class ExecutorTest {

    public static void main(String[] args) {
        testExecutor();
    }

    // Fixed thread pool
    private static void testExecutor() {
        ExecutorService exec = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 100; i++) {
            exec.execute(() -> System.out.println("id: " + Thread.currentThread().getId()));
        }
        exec.shutdown();

        try {
            boolean ret = exec.awaitTermination(10, TimeUnit.SECONDS);
            if (!ret)
                System.out.println("Terminate due to timeout...");
            else
                System.out.println("We are done...");
        } catch (Exception ex) {

        }
    }
}
