package fun.multithread.ratecontrol;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicLong;

/**
 * Limit the number of access to a shared resource. Like message queue etc.
 *
 * Created by lovefly1983.
 */
public abstract class FixRateHandler<K, V> implements MessageHandler<K, V> {

    protected static final Logger logger = LoggerFactory.getLogger(FixRateHandler.class);

    private final AtomicLong numberOfMessages = new AtomicLong(0);

    private int maxNumOfMessages = 500;

    protected FixRateHandler(int maxNumOfMessages) {
        this.maxNumOfMessages = maxNumOfMessages;
    }

    protected String subOnMessage(String topic, K key) { return ""; }

    @Override
    final public String onMessage(String topic, K key, V value) throws Exception {
        synchronized (this) {
            // Wait for the others to wake it up
            while (numberOfMessages.get() == this.maxNumOfMessages) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    // no-op
                }
            }
        }

        numberOfMessages.incrementAndGet();
        return subOnMessage(topic, key);
    }

    /**
     * Need manually call this method.
     */
    final public synchronized void markProcessed() {
        if (numberOfMessages.get() == 0) {
            logger.error("Calling markProcessed wrongly, please check your usage ...");
            return;
        }
        if (numberOfMessages.get() == this.maxNumOfMessages) {
            notifyAll();
        }
        numberOfMessages.decrementAndGet();
    }
}