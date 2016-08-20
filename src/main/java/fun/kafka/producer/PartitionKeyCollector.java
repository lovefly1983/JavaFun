package fun.kafka.producer;

/**
 * Created by lovefly1983.
 */
public interface PartitionKeyCollector<T> {
    public byte[] getPartitionKey(T message);
}
