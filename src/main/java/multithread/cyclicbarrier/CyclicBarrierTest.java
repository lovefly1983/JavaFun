package multithread.cyclicbarrier;

/**
 * Created by lovefly1983.
 */

import java.util.concurrent.*;

public class CyclicBarrierTest {

    public static void main(String[] args) throws InterruptedException {
        // This action is only execute only when it reaches the barrier by one of the thread.
        Runnable action = () -> System.out.printf("Thread %s " + "executing barrier action.%n", Thread.currentThread().getName());

        final CyclicBarrier barrier = new CyclicBarrier(3, action);
        Runnable task = () ->
        {
            String name = Thread.currentThread().getName();
            System.out.printf("%s about to join game...%n", name);
            try {
                barrier.await();
            } catch (BrokenBarrierException bbe) {
                System.out.println("barrier is broken");
                return;
            } catch (InterruptedException ie) {
                System.out.println("thread interrupted");
                return;
            }
            System.out.printf("%s has joined game%n", name);
        };

        int poolSize = 3;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.submit(task);
        }
        // The main difference between CyclicBarrier and CountdownLatch...
        System.out.println("###############Reuse the  barrier");
        for (int i = 0; i < poolSize; i++) {
            executorService.submit(task);
        }

        executorService.shutdown();
        executorService.awaitTermination(10, TimeUnit.MINUTES);
    }
}

