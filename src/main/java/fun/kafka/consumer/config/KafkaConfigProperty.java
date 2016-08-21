package fun.kafka.consumer.config;

import java.util.EnumSet;

public enum KafkaConfigProperty {

    GROUP_ID("group.id", null),

    // default properties
    ZOOKEEPER_CONNECT_URL("zookeeper.connect", "localhost:2181"),
    ZOOKEEPER_SESSION_TIMEOUT_MS("zookeeper.session.timeout.ms", "15000"),
    ZOOKEEPER_SYNC_TIME_MS("zookeeper.sync.time.ms", "200"),
    AUTO_COMMIT_ENABLE("auto.commit.enable", "false"),
    AUTO_OFFSET_RESET("auto.offset.reset", "smallest");

    private final String propertyName;
    private final String defaultValue;

    public static final EnumSet<KafkaConfigProperty> DEFAULT_CONSUMER_CONFIGS_PROPS = EnumSet.of(ZOOKEEPER_SESSION_TIMEOUT_MS,
            ZOOKEEPER_SYNC_TIME_MS, AUTO_COMMIT_ENABLE,
            AUTO_OFFSET_RESET);

    KafkaConfigProperty(String propertyName, String defaultValue) {
        this.propertyName = propertyName;
        this.defaultValue = defaultValue;
    }

    public String propertyName() {
        return propertyName;
    }
    public String defaultValue() {
        return defaultValue;
    }
}
