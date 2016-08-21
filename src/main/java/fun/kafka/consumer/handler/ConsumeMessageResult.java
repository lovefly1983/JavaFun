package fun.kafka.consumer.handler;

public class ConsumeMessageResult implements IConsumeMessageResult {

    public static final ConsumeMessageResult SUCCEED = new ConsumeMessageResult(true);
    public static final ConsumeMessageResult FAIL = new ConsumeMessageResult(false);
    private final boolean wasProcessed;

    public ConsumeMessageResult(boolean wasProcessed) {
        this.wasProcessed = wasProcessed;
    }

    public boolean wasProcessed() {
        return wasProcessed;
    }
}
