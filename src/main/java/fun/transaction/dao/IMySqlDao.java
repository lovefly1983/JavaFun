package fun.transaction.dao;

import fun.transaction.IPersistedRecord;

/**
 * Created by lovefly1983.
 */
public interface IMySqlDao<T extends IPersistedRecord, K> {
    void write(T record);
    T read(K key);
}
