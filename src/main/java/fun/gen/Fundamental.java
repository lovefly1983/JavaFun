package fun.gen;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

/**
 * @author cwang
 */
public class Fundamental {

    /**
     * @param args
     */
    @SuppressWarnings("unchecked")
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        Class<?> cls = String.class;
        System.out.println("class name: " + cls.getName());


        Set<?> setOfUnknownType = new LinkedHashSet<String>();
        setOfUnknownType = new LinkedHashSet<Integer>();

        Set a = new HashSet<String>();
        a.add("abc");
        a.add(1.0e1);

        List items = new ArrayList();
        items.add("chocolates");
        String item = (String) items.get(0);

        List<String> items_ = new ArrayList();
        items_.add("biscuits");
        String item_ = items_.get(0); //no cast required


        ArrayMS<Integer> arr = new ArrayMS<Integer>(10);
        arr.put(100);
        System.out.println(arr.get(0));

        HashMap<String, Set<Integer>> contacts = newContacts();
        //HashMap<String, Set<Integer>> contacts2 = new HashMap<>(); - available since 1.7

        ArrayList<?> unknownList = new ArrayList<Number>();
        unknownList = new ArrayList<Float>();

        ArrayList<? extends Number> numberList = new ArrayList<Number>();
        numberList = new ArrayList<Integer>();
        numberList = new ArrayList<Float>();

        ArrayList<? super Integer> numberList2 = new ArrayList<Number>();
        numberList2 = new ArrayList<Integer>();
        //numberList2 = new ArrayList<Float>(); //compilation error
    }

    //type inference in g method
    public static <K, V> HashMap<K,V> newContacts() {
       return new HashMap<K,V>();
    }
}

class SuccessfulConversion {
    public static <T> T[] toArray(Collection<T> c, T[] a) {
        if (a.length < c.size())
            a = (T[]) Array.newInstance( // unchecked cast
                    a.getClass().getComponentType(), c.size());
        int i = 0;
        for (T x : c)
            a[i++] = x;
        if (i < a.length)
            a[i] = null;
        return a;
    }

    public static <T> T[] toArray(Collection<T> c, Class<T> k) {
        T[] a = (T[]) Array. // unchecked cast
                newInstance(k, c.size());
        int i = 0;
        for (T x : c)
            a[i++] = x;
        return a;
    }
}

class ArrayMS<T> {
    private T[] arr;
    private int index = 0;
    ArrayMS(int size) {
        arr = (T[]) new Object[size]; // unsafe form and workaround
    }

    void put(T a) {
        if (index < arr.length - 1)
            arr[index++] = a;
    }

    T get(int i) {
        if (i >= 0 && i <= index)
            return arr[i];
        return null;
    }

    public Set<T> newContacts2() {
        return new HashSet<T>();
     }
}