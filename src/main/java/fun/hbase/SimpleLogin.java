package fun.hbase;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.hbase.HBaseConfiguration;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.ConnectionFactory;

import java.io.IOException;

/**
 * Created by qingyun on 10/22/17.
 */
public class SimpleLogin implements HBaseLogin {
    @Override
    public Connection login() throws IOException {
        Configuration conf = HBaseConfiguration.create();
        conf.set("hbase.zookeeper.property.clientPort", "2181");
        conf.set("hbase.zookeeper.quorum", "127.0.0.1");
        conf.set("zookeeper.znode.parent", "/hbase");
        return ConnectionFactory.createConnection(conf);
    }
}
