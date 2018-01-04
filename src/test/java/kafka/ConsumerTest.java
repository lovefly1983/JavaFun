package kafka;

import fun.kafka.consumer.ConsumersManager;
import fun.kafka.consumer.handler.StringMessageHandler;
import fun.kafka.producer.KafkaMessageProducer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

/**
 * @author lovefly1983
 */
public class ConsumerTest {
    private static ConsumersManager consumersManager;
    private static LocalZookeeper localZookeeper;
    private static LocalKafka localKafka;
    private static final String ZOOKEEPER_URL = "localhost:2181";
    private static final String BROKER_LIST = "localhost:9092";
    private static final String GROUP_NAME= "test-group";
    private static final String TOPIC_NAME = "test-topic";
    private static final KafkaMessageProducer kafkaMessageProducer = KafkaMessageProducer.buildWithDefaultPartitioner(BROKER_LIST, new StringSerializer());

    @BeforeClass
    public static void setupClass() throws InterruptedException, IOException {
        localZookeeper = new LocalZookeeper(2181);
        List<Integer> kafkaPorts = new ArrayList<>();
        kafkaPorts.add(9092);
        localKafka = new LocalKafka(localZookeeper.getConnection(), new Properties(), kafkaPorts);

        localZookeeper.startup();
        System.out.println("### Embedded Zookeeper connection: " + localZookeeper.getConnection());
        localKafka.startup();
        System.out.println("### Embedded Kafka cluster broker list: " + localKafka.getBrokerList());
    }
    
    @Test
    public void testConsumer() throws Exception {
        //1. Produce messages
        kafkaMessageProducer.sendMessage(TOPIC_NAME, "test-message");

        //2. Consumer messages
        ConsumersManager.ConsumersManagerBuilder builder = new ConsumersManager.ConsumersManagerBuilder();
        StringMessageHandler messageHandler = new StringMessageHandler();
        consumersManager = builder
                .withZookeeperUrl(ZOOKEEPER_URL)
                .withConsumerGroupName(GROUP_NAME)
                .withTopic(TOPIC_NAME)
                .withNumOfConsumers(1)
                .build();
        consumersManager.startConsumers(messageHandler);
        Thread.sleep(10000);
    }

    @AfterClass
    public static void tearDown() {
        // Producer
        kafkaMessageProducer.close();

        // Consumer
        if (consumersManager != null) {
            consumersManager.shutdown();
        }

        localKafka.shutdown();
        localZookeeper.shutdown();
    }
}
