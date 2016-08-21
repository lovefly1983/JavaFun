package fun.kafka.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.ByteArraySerializer;
import org.apache.kafka.common.serialization.Serializer;

import java.util.Properties;

/**
 * Created by lovefly1983.
 */
public class KafkaMessageProducer <T, S extends Serializer<T>>{
    private KafkaProducer<byte[], T> kafkaProducer;
    private final PartitionKeyCollector partitionKeyCollector;

    protected KafkaMessageProducer(String bootstrapServers, S valueSerializer,
                              PartitionKeyCollector partitionKeyCollector) {
        if (partitionKeyCollector != null) {
            this.partitionKeyCollector = partitionKeyCollector;
        } else {
            this.partitionKeyCollector = new StringPartitionKeyCollector();
        }

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.ACKS_CONFIG, "all");
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, ByteArraySerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, valueSerializer.getClass().getName());
        this.kafkaProducer = new KafkaProducer<>(props);
    }

    public void sendMessage(String topic, T message) {
        byte[] partitionKey = this.partitionKeyCollector.getPartitionKey(message);
        this.sendMessage(topic, partitionKey, message);
    }

    public void sendMessage(String topic, byte[] partitionKey, T message) {
        ProducerRecord<byte[], T> producerRecord = new ProducerRecord<>(topic, partitionKey, message);
        this.kafkaProducer.send(producerRecord);
    }

    /**
     * Close the created Kafka producer.
     */
    public void close() {
        this.kafkaProducer.close();
    }

    public static <T, S extends Serializer<T>> KafkaMessageProducer build(String bootstrapServers, S valueSerializer, PartitionKeyCollector<T> partitionKeyCollector) {
        return new KafkaMessageProducer<>(bootstrapServers, valueSerializer, partitionKeyCollector);
    }

    public static <S extends Serializer<String>> KafkaMessageProducer buildWithDefaultPartitioner(String bootstrapServers, S valueSerializer) {
        return build(bootstrapServers, valueSerializer, new StringPartitionKeyCollector());
    }
}
