package fun.kafka.consumer.handler;

public interface MessageHandler<K, V> {
    default IMessageResult onMessage(String topic, K key, V value) throws Exception {
        return null;
    }
}
