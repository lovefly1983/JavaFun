package fun.multithread.countdownlatch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * Created by lovefly1983.
 */
public class CountDownLatchTest {

    private static final int NUM_OF_ACTIONS = 3;
    private static final CountDownLatch latch = new CountDownLatch(NUM_OF_ACTIONS);
    private static final Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) throws InterruptedException {
        ShutdownHook sample = new ShutdownHook();
        sample.attachShutDownHook();

        // The children threads...
        Runnable action = () -> {
            sample.attachShutDownHook();

            System.out.println("Do the action in thread: " + Thread.currentThread().getName());
            logger.info("Thread daemon flag: {}", Thread.currentThread().isDaemon());
            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                logger.error("Exception thrown", e);
            }
            latch.countDown();
        };
        ExecutorService executorService = Executors.newFixedThreadPool(3);
        for (int i = 0; i < NUM_OF_ACTIONS; i++) {
            executorService.submit(action);
        }

        // Wait until all the children thread completes their tasks.
        try {
            latch.await();
            System.out.println("After all the threads finishes, we continue from here...");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        executorService.shutdown();
        executorService.awaitTermination(5, TimeUnit.MILLISECONDS);
        logger.info("main thread terminated");
        // Even though the main thread completes here, but the whole application will not end until
        // the child threads are done.
    }
}

class ShutdownHook {
    public void attachShutDownHook() {
        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                System.out.println("exit");
            }
        });

    }
}

