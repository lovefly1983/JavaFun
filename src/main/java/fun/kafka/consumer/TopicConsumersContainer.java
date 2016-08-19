package fun.kafka.consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.TimeUnit;

public class TopicConsumersContainer {

    private static final Logger LOG = LoggerFactory.getLogger(TopicConsumersContainer.class);

    private static final long SHUTDOWN_WAIT_TIME = 5;

    private final String topic;
    private final ExecutorService executorService;
    private final List<MessageConsumer> messageConsumers;

    protected TopicConsumersContainer(String topic, ExecutorService executorService) {
        this.topic = topic;
        this.messageConsumers = new LinkedList<MessageConsumer>();
        this.executorService = executorService;
    }

    public String getTopic() {
        return topic;
    }

    protected void startConsumer(MessageConsumer messageConsumer) {
        this.messageConsumers.add(messageConsumer);
        this.executorService.submit(messageConsumer);
    }

    protected void shutdown() {
        LOG.info("Shutting Down consumer threads on Topic {}", topic);
        messageConsumers.stream().forEach(messageConsumer -> messageConsumer.close());
        try {
            this.executorService.shutdown();
            this.executorService.awaitTermination(SHUTDOWN_WAIT_TIME, TimeUnit.SECONDS);
        } catch (InterruptedException ie) {
            LOG.warn("Caught exception while shutdown on topic {}.", topic, ie);
            this.executorService.shutdownNow();
        }
    }
}
