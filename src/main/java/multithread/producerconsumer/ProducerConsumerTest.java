package multithread.producerconsumer;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by lovefly1983.
 */
public class ProducerConsumerTest {

    private final static int MAX_MESSAGES_IN_QUEUE = 5;
    private static List<String> queue = new ArrayList();
    private static final Object lock = new Object[0];

    public static void main(String[] args) {
        testProducerAndConsumer();
    }

    private static void testProducerAndConsumer() {
        Producer producer = new Producer();
        Thread consumer = new Consumer();

        producer.generateMessages();
        consumer.start();
    }

    private static class Producer {
        ExecutorService executorService;
        public Producer() {
            executorService = Executors.newFixedThreadPool(5);
        }

        public void generateMessages() {
            for (int i = 0; i < 10; i++) {
                putMessage("Message " + i);
            }
        }
        private void putMessage(String message) {
            executorService.submit(() -> {
                synchronized (lock) {
                    while (queue.size() == MAX_MESSAGES_IN_QUEUE) {
                        try {
                            lock.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Put message : " + message);
                    queue.add(message);
                    lock.notifyAll();
                }
            });
        }
    }

    private static class Consumer extends Thread {
        public void run() {
            while(true) {
                consume();
            }
        }

        private static void consume() {
            synchronized (lock) {
                while (queue.size() == 0) {
                    try {
                        lock.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                String id = queue.remove(0);
                System.out.println("Consume id : " + id);
                lock.notifyAll();
            }
        }
    }


}
