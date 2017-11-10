package fun.hbase;

import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

/**
 * Created by qingyun on 10/22/17.
 */
public interface HBaseLogin {
    Connection login() throws IOException;
}
