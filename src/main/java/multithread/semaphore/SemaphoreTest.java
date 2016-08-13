package multithread.semaphore;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * Created by lovefly1983.
 */
public class SemaphoreTest {

    public static void main(String[] args) {
        final Pool pool = new Pool();
        Runnable r = () -> {
            String name = Thread.currentThread().getName();
            try {
                while (true) {
                    String item;
                    System.out.printf("%s acquiring %s%n", name, item = pool.getItem());
                    Thread.sleep(200 + (int) (Math.random() * 100));
                    System.out.printf("%s putting back %s%n", name, item);
                    pool.putItem(item);
                }
            } catch (InterruptedException ie) {
                System.out.printf("%s interrupted%n", name);
            }

        };
        int poolSize = Pool.MAX_AVAILABLE + 5;
        ExecutorService executorService = Executors.newFixedThreadPool(poolSize);
        for (int i = 0; i < poolSize; i++) {
            executorService.submit(r);
        }
    }
}

final class Pool {
    public static final int MAX_AVAILABLE = 10;

    private Semaphore available = new Semaphore(MAX_AVAILABLE, true);
    private String[] items;
    private boolean[] used = new boolean[MAX_AVAILABLE];

    Pool() {
        items = new String[MAX_AVAILABLE];
        for (int i = 0; i < items.length; i++)
            items[i] = "ITEM" + i;
    }

    String getItem() throws InterruptedException {
        available.acquire();
        return getNextAvailableItem();
    }

    void putItem(String item) {
        if (markAsUnused(item))
            available.release();
    }

    private synchronized String getNextAvailableItem() {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (!used[i]) {
                used[i] = true;
                return items[i];
            }
        }
        return null; // not reached
    }

    private synchronized boolean markAsUnused(String item) {
        for (int i = 0; i < MAX_AVAILABLE; ++i) {
            if (item == items[i]) {
                if (used[i]) {
                    used[i] = false;
                    return true;
                } else
                    return false;
            }
        }
        return false;
    }
}