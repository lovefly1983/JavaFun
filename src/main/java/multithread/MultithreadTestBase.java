package multithread;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lovefly1983.
 */
public class MultithreadTestBase {
    private static final int NUM_OF_ACTIONS = 3;
    private ExecutorService executorService;

    public MultithreadTestBase() {
        this(NUM_OF_ACTIONS);
    }

    public MultithreadTestBase(int maxNumOfActions) {
        executorService = Executors.newFixedThreadPool(maxNumOfActions);
    }

    protected void submitTask(Runnable task) {
        for (int i = 0; i < NUM_OF_ACTIONS; i++) {
            executorService.submit(task);
        }
    }
}
