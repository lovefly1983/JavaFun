import fun.SolutionA;
import org.apache.hadoop.hbase.util.Bytes;
import org.junit.Assert;
import org.junit.Test;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

/**
 * Created by chunjiewang on 7/23/17.
 */
public class TestSolutionA {
    @Test
    public void ttt() {
        String x = "中国111xx";
        System.out.println(new String(x.getBytes()));
    }

    @Test
    public void testA() {
        SolutionA sa = new SolutionA();
        int[] h = {2, 2, 3};
        int[] w = {3, 1};
        Assert.assertEquals(1, sa.maxNumOfPerformer(h, w));
    }

    @Test
    public void testB() {
        SolutionA sa = new SolutionA();
        int[] h = {2};
        int[] w = {1};
        Assert.assertEquals(0, sa.maxNumOfPerformer(h, w));
    }

    @Test
    public void testC() {
        SolutionA sa = new SolutionA();
        int[] h = {2};
        int[] w = {3};
        Assert.assertEquals(1, sa.maxNumOfPerformer(h, w));
    }

    @Test
    public void testD() {
        SolutionA sa = new SolutionA();
        int[] h = {2};
        int[] w = {2};
        Assert.assertEquals(1, sa.maxNumOfPerformer(h, w));
    }


    @Test
    public void testE() {
        SolutionA sa = new SolutionA();
        int[] h = {1, 2, 3};
        int[] w = {1, 1};
        Assert.assertEquals(1, sa.maxNumOfPerformer(h, w));
    }

    @Test
    public void testF() {
        SolutionA sa = new SolutionA();
        int[] h = {1, 2};
        int[] w = {1, 2, 3};
        Assert.assertEquals(2, sa.maxNumOfPerformer(h, w));
    }

    @Test

    public void tt() throws NoSuchAlgorithmException {
        // long
//
        long l = 1234567890L;
        byte[] lb = Bytes.toBytes(l);
        System.out.println("long bytes length: " + lb.length);   // returns 8

        String s = String.valueOf(l);
        byte[] sb = Bytes.toBytes(s);
        System.out.println("long as string length: " + sb.length);    // returns 10

// hash
//
        MessageDigest md = MessageDigest.getInstance("MD5");
        byte[] digest = md.digest(Bytes.toBytes(s));
        System.out.println("md5 digest bytes length: " + digest.length);    // returns 16
        System.out.println("md5 digest: " + digest);    // returns 16



        String sDigest = new String(digest);
        byte[] sbDigest = Bytes.toBytes(sDigest);
        System.out.println("value: " + sDigest);
        System.out.println("md5 digest as string length: " + sbDigest.length);    // returns 26
    }


}
