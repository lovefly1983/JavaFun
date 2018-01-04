package fun.hbase;

import org.apache.hadoop.hbase.Cell;
import org.apache.hadoop.hbase.HColumnDescriptor;
import org.apache.hadoop.hbase.HTableDescriptor;
import org.apache.hadoop.hbase.TableName;
import org.apache.hadoop.hbase.client.Admin;
import org.apache.hadoop.hbase.client.Connection;
import org.apache.hadoop.hbase.client.Get;
import org.apache.hadoop.hbase.client.Put;
import org.apache.hadoop.hbase.client.Result;
import org.apache.hadoop.hbase.client.Table;
import org.apache.hadoop.hbase.util.Bytes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by lovefly1983 on 10/16/17.
 */
public class HBastTest {
    public static void main(String[] args) throws IOException {
//        Logger logger = LoggerFactory.getLogger(HBastTest.class);
//        TableName tableName = TableName.valueOf("stock-prices");
//
//        logger.info("login hbase ...");
//        Connection conn = loginNoAuth();
//
//        Admin admin = conn.getAdmin();
//        if (!admin.tableExists(tableName)) {
//            admin.createTable(new HTableDescriptor(tableName).addFamily(new HColumnDescriptor("cf")));
//        }
//
//        logger.info("Create table ....");
//        Table table = conn.getTable(tableName);
//        Put p = new Put(Bytes.toBytes("AAPL10232015"));
//        p.addColumn(Bytes.toBytes("cf"), Bytes.toBytes("close"), Bytes.toBytes(222100));
//        table.put(p);
//
//        logger.info("ops on the table");
//        Result r = table.get(new Get(Bytes.toBytes("AAPL10232015")));
//        for (Cell cell : r.rawCells()) {
//            logger.info("cell row: " + new String(cell.getRowArray(), cell.getRowOffset(), cell.getRowLength()));
//            logger.info("cell family: " + new String(cell.getFamilyArray(), cell.getFamilyOffset(), cell.getFamilyLength()));
//            logger.info("cell qualifier: " + new String(cell.getQualifierArray(), cell.getQualifierOffset(), cell.getQualifierLength()));
//            logger.info("cell value: " + Bytes.toInt(cell.getValueArray(), cell.getValueOffset(), cell.getValueLength()));
//            logger.info("cell timestamp: " + cell.getTimestamp());
//        }
    }

    private static Connection loginNoAuth() throws IOException {
        return new SimpleLogin().login();
    }

    private static Connection loginKerberos() throws IOException {
        return new KerberosLogin().login();
    }
}
