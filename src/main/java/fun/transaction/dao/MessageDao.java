package fun.transaction.dao;

import fun.transaction.Message;
import fun.transaction.MessageIdentifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Created by lovefly1983.
 */
public class MessageDao implements IMySqlDao<Message, MessageIdentifier>{
    private static final Logger log = LoggerFactory.getLogger(MessageDao.class);
    @Override
    public void write(Message record) {
        log.info("Write record into db: {}", record);
    }

    @Override
    public Message read(MessageIdentifier key) {
        log.info("Read message from db with key: {}", key);
        return null;
    }
}
