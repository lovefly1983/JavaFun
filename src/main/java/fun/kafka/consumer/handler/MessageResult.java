package fun.kafka.consumer.handler;

public class MessageResult implements IMessageResult {

    private final boolean wasProcessed;

    public MessageResult(boolean wasProcessed) {
        this.wasProcessed = wasProcessed;
    }

    public boolean wasProcessed() {
        return wasProcessed;
    }
}
