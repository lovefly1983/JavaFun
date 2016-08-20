package fun.kafka.producer;

/**
 * Created by lovefly1983.
 */
public class StringPartitionKeyCollector implements PartitionKeyCollector<String> {
    @Override
    public byte[] getPartitionKey(String message) {
        if (message != null && message.length() > 0) {
            //System.out.println("####1!!!" + message.getBytes().length + "&&&&&" + message.length());
            return new byte[] {message.getBytes()[0] };
        }
        return "dummy".getBytes();
    }
}
