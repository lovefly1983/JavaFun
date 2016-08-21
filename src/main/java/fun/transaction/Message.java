package fun.transaction;

/**
 * Created by lovefly1983.
 */
public class Message implements IPersistedRecord {
    @Override
    public byte[] getBody() {
        return new byte[0];
    }
}
