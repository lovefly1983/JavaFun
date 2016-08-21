package fun.kafka.consumer.handler;

public interface MessageHandler<K, V> {
    default IConsumeMessageResult onMessage(String topic, K key, V value) throws Exception {
        return ConsumeMessageResult.SUCCEED;
    }
}
