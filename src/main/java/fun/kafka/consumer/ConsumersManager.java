package fun.kafka.consumer;

import fun.kafka.consumer.config.KafkaConfigProperty;
import fun.kafka.consumer.handler.DefaultMessageHandler;
import fun.kafka.consumer.handler.MessageHandler;
import fun.kafka.consumer.handler.StringMessageHandler;
import kafka.consumer.ConsumerConfig;
import kafka.javaapi.consumer.ConsumerConnector;
import kafka.serializer.Decoder;
import kafka.serializer.DefaultDecoder;
import kafka.serializer.StringDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicBoolean;

/**
 * User the builder to new a ConsumersManger and use it once for each topic.
 */
public final class ConsumersManager {
    protected static final Logger LOG = LoggerFactory.getLogger(ConsumersManager.class);

    private TopicConsumersContainer topicConsumersContainer;
    private final AtomicBoolean isClosed;
    private final AtomicBoolean isInitialized;

    private final Properties configProperties;
    private final String topicName;
    private final int numConsumers;
    private final String appName;

    private ConsumersManager(Properties configProperties, String appName, String topicName, int numConsumers) {
        this.configProperties = configProperties;
        this.isClosed = new AtomicBoolean(false);
        this.isInitialized = new AtomicBoolean(false);
        this.appName = appName;
        this.topicName = topicName;
        this.numConsumers = numConsumers;
    }

    protected static final Map<String, Integer> toTopicConsumerCountMap(String topic, int numConsumers) {
        Map<String, Integer> topicConsumerCountMap = new HashMap<String, Integer>();
        topicConsumerCountMap.put(topic, numConsumers);
        return topicConsumerCountMap;
    }
    public void startConsumers(StringMessageHandler messageHandler) throws Exception {
        startConsumers(messageHandler, new StringDecoder(null), new StringDecoder(null));
    }

    /**
     * Start the consumers.
     */
    public <K, V> void startConsumers(MessageHandler<K, V> messageHandler, Decoder<K> keySerializer, Decoder<V> valueSerializer) throws Exception {
        ConsumerConfig consumerConfig = new ConsumerConfig(configProperties);
        startConsumers(messageHandler, consumerConfig, toTopicConsumerCountMap(topicName, numConsumers), keySerializer, valueSerializer);
    }

    public <K, V> void startConsumers(MessageHandler<K, V> messageHandler, ConsumerConfig consumerConfig,
                                      Map<String, Integer> topicCountMap, Decoder<K> keyDeserializer, Decoder<V> valueDeserialzier) {
        if (this.isClosed.get()) {
            throw new IllegalStateException("Cannot start consumers since container is already closed!");
        }
        if (this.isInitialized.get()) {
            throw new IllegalStateException("Can not start the consumers once it has already started");
        }
        this.isInitialized.getAndSet(true);

        topicCountMap.forEach((topic, numThreads) -> {
            ExecutorService executorService = Executors.newFixedThreadPool(numThreads);
            TopicConsumersContainer topicConsumersContainer = new TopicConsumersContainer(topic, executorService);
            for (int i = 0; i < numThreads; i++) {
                // Each thread will have a connector so that they will not affect each other.
                ConsumerConnector consumerConnector = kafka.consumer.Consumer.createJavaConsumerConnector(consumerConfig);
                MessageConsumer messageConsumer = new MessageConsumer<>(this.appName, messageHandler, consumerConnector, topic, i, keyDeserializer, valueDeserialzier);
                topicConsumersContainer.startConsumer(messageConsumer);
            }
            this.topicConsumersContainer = topicConsumersContainer;
        });
        LOG.info("********Kafka consumer threads started......");
    }

    // Call this after we are done.
    public void shutdown() {
        isClosed.getAndSet(true);
        if (topicConsumersContainer != null) {
            LOG.info("Shutting down consumers for topic {}.", topicConsumersContainer.getTopic());
            topicConsumersContainer.shutdown();
        }
    }

    /**
     * The builder for the Consumers Manager.
     */
    public static class ConsumersManagerBuilder {
        private String zookeeperUrl;
        private String consumerGroupName = "default-consumer-group";
        private String topic = "unknown-topic-name";
        private String appName = "default-app-name";
        private int numOfConsumers;
        private Properties configProperties;

        public ConsumersManagerBuilder withZookeeperUrl(String connectionUrl) {
            this.zookeeperUrl = connectionUrl;
            return this;
        }

        public ConsumersManagerBuilder withConsumerGroupName(String consumerGroupName) {
            this.consumerGroupName = consumerGroupName;
            return this;
        }

        public ConsumersManagerBuilder withTopic(String topic) {
            this.topic = topic;
            return this;
        }

        public ConsumersManagerBuilder withAppName(String appName) {
            this.appName = appName;
            return this;
        }

        public ConsumersManagerBuilder withNumOfConsumers(int numOfConsumers) {
            this.numOfConsumers = numOfConsumers;
            return this;
        }

        /**
         * For customized properties.
         */
        public ConsumersManagerBuilder withConfigProperties(Properties configProperties) {
            this.configProperties = configProperties;
            return this;
        }

        /**
         * Build the ConsumersManager
         */
        public ConsumersManager build() {
            Properties validProperties = createConfigProperties(validateZookeeperUrl());
            return new ConsumersManager(validProperties, this.appName, this.topic, this.numOfConsumers);
        }

        private String validateZookeeperUrl() {
            String zookeeperUrl = this.zookeeperUrl;
            if (zookeeperUrl == null || zookeeperUrl.trim().isEmpty()) {
                throw new RuntimeException("Zookeeper url cannot be empty.");
            }
            return zookeeperUrl;
        }

        private Properties createConfigProperties(String zookeeperUrl) {
            // User provided one
            final Properties properties = this.configProperties != null ? this.configProperties : new Properties();
            properties.setProperty(KafkaConfigProperty.ZOOKEEPER_CONNECT_URL.propertyName(), zookeeperUrl);
            properties.setProperty(KafkaConfigProperty.GROUP_ID.propertyName(), consumerGroupName);

            // Set the default properties if it is not set yet.
            KafkaConfigProperty.DEFAULT_CONSUMER_CONFIGS_PROPS.stream().forEach(kafkaConfigProperty -> {
                if (!properties.contains(kafkaConfigProperty.propertyName())) {
                    properties.setProperty(kafkaConfigProperty.propertyName(), kafkaConfigProperty.defaultValue());
                }
            });
            return properties;
        }
    }
}
