package kafka;

import fun.kafka.consumer.ConsumersManager;
import fun.kafka.consumer.handler.IMessageResult;
import fun.kafka.consumer.handler.MessageHandler;
import kafka.serializer.DefaultDecoder;
import org.junit.Test;

/**
 * @author cwang
 */
public class ConsumerTest {
    @Test
    public void testConsumer() throws Exception {
        ConsumersManager.ConsumersManagerBuilder builder = new ConsumersManager.ConsumersManagerBuilder();
        MessageHandler<byte[], byte[]> messageHandler = new MessageHandler<byte[], byte[]>() {};
        ConsumersManager consumersManager = builder
                .withZookeeperUrl("localhost:2181")
                .withConsumerGroupName("group1")
                .withTopic("topic")
                .withNumOfConsumers(2)
                .build();
        //consumersManager.startConsumers(messageHandler, new DefaultDecoder(null), new DefaultDecoder(null));
        consumersManager.shutdown();
    }
}
