package fun.multithread.ratecontrol;

/**
 * Created by lovefly1983.
 */
public interface MessageHandler<K, V> {
    public String onMessage(String topic, K key, V value) throws Exception;
}
