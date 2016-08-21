package kafka;

/**
 * Created by lovefly1983.
 */
import org.apache.zookeeper.server.NIOServerCnxnFactory;
import org.apache.zookeeper.server.ServerCnxnFactory;
import org.apache.zookeeper.server.ZooKeeperServer;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.InetSocketAddress;

public class LocalZookeeper {
    private int port = -1;
    private int tickTime = 500;

    private ServerCnxnFactory factory;
    private File snapshotDir;
    private File logDir;

    public LocalZookeeper() {
        this(-1);
    }

    public LocalZookeeper(int port) {
        this(port, 500);
    }

    public LocalZookeeper(int port, int tickTime) {
        this.port = port;
        this.tickTime = tickTime;
    }

    public void startup() throws IOException{
        this.factory = NIOServerCnxnFactory.createFactory(new InetSocketAddress("localhost", port), 1024);
        this.snapshotDir = TestUtils.constructTempDir("embeeded-zk/snapshot");
        this.logDir = TestUtils.constructTempDir("embeeded-zk/log");

        try {
            factory.startup(new ZooKeeperServer(snapshotDir, logDir, tickTime));
        } catch (InterruptedException e) {
            throw new IOException(e);
        }
    }


    public void shutdown() {
        factory.shutdown();
        try {
            TestUtils.deleteFile(snapshotDir);
        } catch (FileNotFoundException e) {
            // ignore
        }
        try {
            TestUtils.deleteFile(logDir);
        } catch (FileNotFoundException e) {
            // ignore
        }
    }

    public String getConnection() {
        return "localhost:" + port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public void setTickTime(int tickTime) {
        this.tickTime = tickTime;
    }

    public int getPort() {
        return port;
    }

    public int getTickTime() {
        return tickTime;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmbeddedZookeeper{");
        sb.append("connection=").append(getConnection());
        sb.append('}');
        return sb.toString();
    }
}
