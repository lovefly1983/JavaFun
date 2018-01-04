package fun.hbase;

import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

/**
 * Created by lovefly1983 on 10/22/17.
 */
public class SimpleLogin implements HBaseLogin {
    @Override
    public Connection login() throws IOException {
//        Configuration conf = HBaseConfiguration.create();
//        conf.set("hbase.zookeeper.property.clientPort", "2181");
//        conf.set("hbase.zookeeper.quorum", "127.0.0.1");
//        conf.set("zookeeper.znode.parent", "/hbase");
//        return ConnectionFactory.createConnection(conf);
        return null;
    }
}
