package multithread.reentrantlock;

import multithread.MultithreadTestBase;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * The main difference between synchronized and ReentrantLock is ability to trying for lock interruptibly, and with timeout.
 * Refer to lockInterruptibly and tryLock.
 *
 * Created by lovefly1983.
 */
public class ReentrantLockTest {

    public static void main(String[] args) {
        MyReentrantLockTest myReentrantLockTest = new MyReentrantLockTest();
        myReentrantLockTest.test();

    }

    private static class MyReentrantLockTest extends MultithreadTestBase {
        public void test() {
            ReentrantLock reentrantLock = new ReentrantLock();
            Runnable task = () ->
            {
                try {
                    reentrantLock.lock();
                    for (int i = 0; i < 9; i++) {
                        System.out.println("Running in thread: " + Thread.currentThread().getName());
                    }
                } finally {
                    reentrantLock.unlock();
                }
            };
            submitTask(task);
        }
    }

    private static class ConditionTest extends MultithreadTestBase {
        final Lock lock = new ReentrantLock();
        final Condition notFull  = lock.newCondition();
        final Condition notEmpty = lock.newCondition();
        int putIndex = 0, getIndex = 0;

        int totalSize = 10;
        final Object[] items = new Object[totalSize];
        int count;

        public void put(Object x) throws InterruptedException {
            lock.lock();
            try {
                while (count == items.length)
                    notFull.await();
                ++count;
                putIndex = putIndex % totalSize;
                items[putIndex++] = x;
                // add into items
                notEmpty.signal();
            } finally {
                lock.unlock();
            }
        }

        public Object take() throws InterruptedException {
            lock.lock();
            try {
                while (count == 0)
                    notEmpty.await();
                getIndex = getIndex % totalSize;
                Object ret = items[getIndex++];
                --count;
                notFull.signal();
                return ret;
            } finally {
                lock.unlock();
            }
        }
    }

}
