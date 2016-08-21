package fun.kafka.consumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lovefly1983.
 */
public class DefaultMessageHandler implements MessageHandler<byte[], byte[]> {
    private static final Logger log = LoggerFactory.getLogger(DefaultMessageHandler.class);
    @Override
    public IConsumeMessageResult onMessage(String topic, byte[] key, byte[] value) throws Exception {
        log.info("Default message handler receives message with key: {} and value: {}", key, value);
        return ConsumeMessageResult.SUCCEED;
    }
}
