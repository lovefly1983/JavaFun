package fun.kafka.producer;

/**
 * Created by lovefly1983.
 */
public class StringPartitionKeyCollector implements PartitionKeyCollector<String> {
    @Override
    public byte[] getPartitionKey(String message) {
        if (message != null && message.length() > 0) {
            return new byte[] {message.getBytes()[0] };
        }
        return "dummy".getBytes();
    }
}
