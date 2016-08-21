package fun.kafka.consumer;

import fun.kafka.consumer.handler.IConsumeMessageResult;
import fun.kafka.consumer.handler.MessageHandler;
import kafka.consumer.ConsumerIterator;
import kafka.consumer.KafkaStream;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.message.MessageAndMetadata;
import kafka.serializer.Decoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class MessageConsumer<K, V> implements Runnable {

    private static final Logger LOG = LoggerFactory.getLogger(MessageConsumer.class);

    private final String applicationName;
    private final MessageHandler<K, V> handler;
    private final ConsumerConnector consumerConnector;
    private final String topic;
    private final int index;
    private final Decoder<K> keyDecoder;
    private final Decoder<V> valueDecoder;

    private final AtomicBoolean isClosed = new AtomicBoolean(false);

    protected MessageConsumer(String applicationName, MessageHandler<K, V> handler,
                              ConsumerConnector consumerConnector, String topic, int index,
                              Decoder<K> keyDecoder, Decoder<V> valueDecoder) {
        this.applicationName = applicationName;
        this.handler = handler;
        this.consumerConnector = consumerConnector;
        this.topic = topic;
        this.index = index;
        this.keyDecoder = keyDecoder;
        this.valueDecoder = valueDecoder;
    }

    /**
     * Read messages from specified Kafka stream
     */
    @Override
    public void run() {
        LOG.debug("Started thread {} for topic: {}.", getConsumerId(), this.topic);
        // Specify as 1 here so that we will only have one stream for one thread, and that stream could serve several
        // partitions.
        Map<String, Integer> topicCountMap = ConsumersManager.toTopicConsumerCountMap(this.topic, 1);
        Map<String, List<KafkaStream<K, V>>> consumerMap = consumerConnector.createMessageStreams(topicCountMap, this.keyDecoder, this.valueDecoder);
        List<KafkaStream<K, V>> streams = consumerMap.get(topic);
        KafkaStream<K, V> stream = streams.get(0);
        ConsumerIterator<K, V> consumerIterator = stream.iterator();

        while (consumerIterator.hasNext()) {
            if (isClosed.get()) {
                break;
            }

            MessageAndMetadata<K, V> msgAndData = consumerIterator.next();
            K key = msgAndData.key();

            IConsumeMessageResult result = null;
            try {
                result = handler.onMessage(this.topic, key, msgAndData.message());
                if (result.wasProcessed()) {
                    this.consumerConnector.commitOffsets();
                }

            } catch (Exception ex) {
                LOG.error("Exception for message with key {}.", key, ex);
            }
        }
        LOG.debug("Closing {} for topic, {}.", getConsumerId(), this.topic);
    }

    public void close() {
        isClosed.getAndSet(true);
        this.consumerConnector.shutdown();
    }

    /**
     * Get log prefix for the consumer thread.
     *
     * @return {@link String}
     */
    private String getConsumerId() {
        return String.format("Listener %d: ", index);
    }
}
