package multithread.producerconsumer;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by lovefly1983.
 */
public class ProducerConsumerTest {

    /**
     * @param args
     */
    public static void main(String[] args) {
        testProducerAndConsumer();
    }

    private static void testProducerAndConsumer() {
        Thread producer = new Producer();
        Thread consumer = new Consumer();
        producer.start();
        consumer.start();
    }

    private static class Producer extends Thread {
        public void run() {
            for (int i = 0; i < 10; i++) {
                produce(i);
            }
            System.out.println("Producer is done...");
        }

        private void produce(int i) {
            synchronized (queue) {
                if (queue.size() == MAXN) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }

                queue.add(i);
                System.out.println("produce id : " + i);
                queue.notifyAll();
            }
        }
    }

    private static class Consumer extends Thread {
        public void run() {
            for (; ; ) {
                consume();
            }
        }

        private static void consume() {
            synchronized (queue) {
                if (queue.size() == 0) {
                    try {
                        queue.wait();
                    } catch (InterruptedException e) {
                        // TODO Auto-generated catch block
                        e.printStackTrace();
                    }
                }
                int id = queue.remove(0);
                System.out.println("Consume id : " + id);
                queue.notifyAll();
            }
        }
    }

    private final static int MAXN = 5;
    private static List<Integer> queue = new ArrayList<Integer>();
}
