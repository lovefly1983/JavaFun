package java8.functional;

import org.junit.After;
import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * @author cwang
 */
public class TestFun {

    // Method reference
    Consumer<Integer> print = new PrintConsumer<>();
    Consumer<String> printStr = new PrintConsumer<>();
    Supplier<String> stringSupplier = String::new;

    class PrintConsumer<T> implements Consumer<T> {
        @Override
        public void accept(T t) {
            System.out.println(t);
        }
    }

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

        Stream<Integer> integerStream = IntStream.of(1, 3, 4).boxed();
        integerStream.filter(x -> x >= 3).forEach(print);

        Supplier<Stream<String>> stringSupplier = () -> Arrays.asList("db1", "bc2").stream();
        stringSupplier.get().sorted().forEach(printStr);

        // Reduce
        Supplier<Stream<Integer>> streamSupplier = () -> Arrays.stream(new int[] {7, 8, 9}).boxed();
        Optional<Integer> reduced = streamSupplier.get().reduce((x, y) -> x + y);
        reduced.ifPresent(print);

        // AnyMatch, NonMatch, AllMatch
        Assert.assertTrue(streamSupplier.get().anyMatch(x -> x > 8));
        Assert.assertFalse(streamSupplier.get().allMatch(x -> x > 7));
        Assert.assertTrue(streamSupplier.get().noneMatch(x -> x < 7));
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

    @FunctionalInterface
    interface Converter<F, T> {
        T convert(F from);
    }

    @Test
    public void testFunction() {
        // Function
        Function<String, Integer> function1 = Integer::valueOf;
        Predicate<String> nonNull = Objects::nonNull;
        Assert.assertTrue(nonNull.test(new String()));

        // Customized FunctionalInterface
        Converter<String, Integer> converter = Integer::valueOf;
        Converter<String, Integer> converter2 = from -> Integer.valueOf(from);
        Assert.assertEquals(123, converter.convert("123").intValue());
        Assert.assertEquals(123, converter2.convert("123").intValue());

        // Comparator
        Comparator<Person> comparator = (p1, p2) -> p1.firstName.compareTo(p2.firstName);
        Person p1 = new Person("a", "b");
        Person p2 = new Person("0", "1");
        List<Person> persons = Arrays.asList(p1, p2);
        persons.sort(comparator);
        persons.stream().forEach(p -> System.out.println(p));
    }

    class Person {
        @Override
        public String toString() {
            return "Person{" +
                    "firstName='" + firstName + '\'' +
                    ", lastName='" + lastName + '\'' +
                    '}';
        }

        public String firstName;
        public String lastName;

        public Person(String a, String b) {
            firstName = a;
            lastName = b;
        }

        public String getFirstName() {
            return firstName;
        }

        public void setFirstName(String firstName) {
            this.firstName = firstName;
        }

        public String getLastName() {
            return lastName;
        }

        public void setLastName(String lastName) {
            this.lastName = lastName;
        }
    }




    private static void printIt(Supplier<Integer> supplier) {
        System.out.println(supplier.get());
    }
}
