package fun.gen;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

/**
 * For all the wildcard of java generics.
 *
 * @author lovefly1983
 */
public class WildcardTest {

    //1. ################# T extends Number vs ? extends Number
    private static class Base {
        // It is the same as the below one
        public <T extends Number> void test(List<T> t) {

        }
    }

    private static class Base2 {
        public void test(List<? extends Number> t) {
        }
    }

    //2. List<Object> vs List<?>
    private void print(List<Object> list) {}

    private void print2(List<?> list) {}
    public void testIt() {
        List<Object> objects = new ArrayList<>();
        print(objects);

        List<String> strings = new ArrayList<>();
        // print(strings); ---- Compliation error
        print2(strings); // It works when using wildcard
    }

    //3. upper bound wildcard
    //List<Number> lst = new ArrayList<Integer>();  // Compilation Error
    List<? extends Number> lst = new ArrayList<Integer>();

    private void upperBound(List<? extends Number> numbers) {
    }

    private void testUpperBound() {
        List<Integer> integers = new ArrayList<>();
        upperBound(integers);
    }

    //4. Lower bound wildcard
    public static <T> T writeAll(Collection<T> coll, List<? super T> snk) {
        T ret = null;
        return ret;
    }

    public static <T> List<? super T> writeAll(Collection<T> coll) {
        T ret = null;
        return new ArrayList<>();
    }

    private static class Bar implements Comparable<Object> {
        @Override
        public int compareTo(Object o) {
            return 0;
        }
    }

    // Need to set ? super T as Bar only implement Comparable<Object> instead of Comparable<Bar>
    public static <T extends Comparable<? super T>> T max(Collection<T> coll) {
        T t = null;
        return t;
    }

    // PECS: producer -- extends, consumer -- supper
    private void testLowerBound() {
        List<Object> snk = new ArrayList<>();
        List<String> input = new ArrayList<>();
        // T is inferred as String
        String result = writeAll(input, snk);
        List<? super String> ret = writeAll(input);

        List<Bar> bars = new ArrayList<>();
        Bar x = max(bars);
    }

    // Import Note:
    // As a consumer, better to be more generic, can NOT be read from it, just write
    // As a producer, better to be more specific, can NOT be written into anything, just READ
    // If you want to both READ & Write ability, don't use the bound !!!
    public static void count(List<? super Integer> ints, int n) {
        for (int i = 0; i < n; i++) ints.add(i);
        // Integer x = ints.get(0);
    }

    private void testPECS() {
        List<Integer>ints = new ArrayList<Integer>();
        count(ints, 5);
        assert ints.toString().equals("[0, 1, 2, 3, 4]");

        List<Number>nums = new ArrayList<Number>();
        count(nums, 5); nums.add(5.0);
        assert nums.toString().equals("[0, 1, 2, 3, 4, 5.0]");

        List<Object>objs = new ArrayList<Object>();
        count(objs, 5); objs.add("five");
        assert objs.toString().equals("[0, 1, 2, 3, 4, five]");

        // For Write only !!!!!
        List<? extends Meat> meats = new ArrayList<>();
        //meats.add(new Pork()); - meats could be anything like Beef, so we could not put anything into it

        // For Read only !!!!!
        List<? super Meat> meats2 = new ArrayList<>();
        meats2.add(new Beef());
        meats2.add(new Pork());
        meats2.add(new Meat());
        //meats2.add(new Food()); could not only add anything which is not subtype of Meat, because meats could be list of Meat.
    }
}
