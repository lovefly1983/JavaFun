import fun.SolutionA;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;

/**
 * Created by chunjiewang on 7/23/17.
 */
public class TestSolutionA {
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
}
