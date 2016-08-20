package fun.transaction;

/**
 * Created by lovefly1983.
 */
public class DistributedTransactionTest {
    public static void main(String[] args) {
        // Service A -> MQ -> Service B
        // 1. Service A Write the message into db like mysql with status pending in local transaction
        // 2. Timer to poll the db table and send message to kafka
        // 3. Service B receives the message and check the message_applied table to decide proceed or not
        // 4. Once the message is processed, if it fails, need rollback the action in Service A
    }
}
