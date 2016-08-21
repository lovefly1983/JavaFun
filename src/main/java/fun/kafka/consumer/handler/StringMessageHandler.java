package fun.kafka.consumer.handler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lovefly1983.
 */
public class StringMessageHandler implements MessageHandler<String, String> {
    private static final Logger log = LoggerFactory.getLogger(StringMessageHandler.class);

    @Override
    public IConsumeMessageResult onMessage(String topic, String key, String value) throws Exception {
        log.debug("###################String message handler receives message with key: {} and value: {}##########", key, value);
        return ConsumeMessageResult.SUCCEED;
    }
}
