package java8.functional;

import org.junit.After;
import org.junit.Test;

import java.util.List;
import java.util.function.Consumer;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 * @author cwang
 */
public class TestFun {

    @After
    public void after() {
        System.out.println("----------------------");
    }

    @Test
    public void testStream() {
        List<Integer> ret = IntStream.of(1, 3, 4).boxed().collect(Collectors.toList());
        ret.stream().forEach(v -> {
            System.out.println(v);
        });
    }

    @Test
    public void testConsumer() {
        Consumer<Integer> consumer = i -> System.out.println(i);
        IntStream.of(4,5,6).boxed().forEach(consumer);
        IntStream.of(4,5,6).boxed().forEach(v -> consumer.andThen(x -> System.out.println("then: " + x++)).accept(v));
    }

    @Test
    public void testSupplier() {
        IntStream.of(8,9,10).boxed().forEach(i -> {
            printIt(() -> i);
        });
    }

    private static void printIt(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }
}
