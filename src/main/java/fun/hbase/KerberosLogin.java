package fun.hbase;

import org.apache.hadoop.hbase.client.Connection;

import java.io.IOException;

/**
 * Created by lovefly1983 on 10/22/17.
 */
public class KerberosLogin implements HBaseLogin{
    @Override
    public Connection login() throws IOException {
        return null;
//        Configuration conf = HBaseConfiguration.create();
//        conf.set("hadoop.security.authentication", "Kerberos");
//        conf.set("hbase.security.authentication", "Kerberos");
//        conf.set("hbase.master.kerberos.principal", "hbase/phoenix.docker.com@EXAMPLE.COM");
//        conf.set("hbase.regionserver.kerberos.principal", "hbase/phoenix.docker.com@EXAMPLE.COM");
//        conf.set("hbase.cluster.distributed", "true");
//        conf.set(HConstants.ZOOKEEPER_QUORUM, "127.0.0.1");
//        conf.set(HConstants.ZOOKEEPER_CLIENT_PORT, "2181");
//        conf.set(HConstants.ZOOKEEPER_ZNODE_PARENT, "/hbase-secure");
//
//        System.setProperty("java.security.krb5.conf", "krb5.conf");
//        System.setProperty("sun.security.krb5.debug", "true");
//        UserGroupInformation.setConfiguration(conf);
//        UserGroupInformation.loginUserFromKeytab("admin/admin@EXAMPLE.COM", "admin.keytab");
//        return ConnectionFactory.createConnection(conf);
    }
}
