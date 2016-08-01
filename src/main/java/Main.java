import java.io.PrintStream;
import java.util.*;

public class Main {

	/**
	 * @param args
	 */
	private static PrintStream out = System.out;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	    testComparalbe();  
	    testInheritance();
	    testGeneric();
	}

	private static void testComparalbe() {
		System.out.println("333333333");
	    // 1. Test Comparable interface
        List<T> l = new ArrayList<T>();


        l.add(new T(3,1));
        l.add(new T(4,2));
        l.add(new T(1,0));
        Collections.sort(l);
        for (T t : l) 
            System.out.println(t.start);
        
        List<Interval> intervals = new ArrayList<Interval>();
        intervals.add(new Interval(2,1));
        intervals.add(new Interval(4,2));
        intervals.add(new Interval(0,0));
        Collections.sort(intervals, new CompareInterval());
        for (Interval t : intervals) 
            System.out.println(t.start);

		Comparator<Integer> cmp = new Comparator<Integer>() {
			@Override
			public int compare(Integer o1, Integer o2) {
				return (o1 < o2 ? -1 : (o1 == o2 ? 0 : 1));
			}
		};
		System.out.println("44444444");
		int x = cmp.compare(new Integer(10), new Integer(10));
		int a = new Integer(10), b = new Integer(10);

		System.out.println(new Integer(10) < new Integer(10));
	}
	
	private static class T implements Comparable<T>{
	    int start, end;
	    public T(int start_, int end_) {
	        start = start_;
	        end = end_;
	    }

        @Override
        public int compareTo(T arg0) {
            // TODO Auto-generated method stub
            return start - arg0.start;
        }
	}
	
	private static class Interval {
	    int start, end;
	    public Interval (int start_, int end_) {
            start = start_;
            end = end_;
        } 
	}
	
	private static class CompareInterval implements Comparator<Interval> {

        @Override
        public int compare(Interval arg0, Interval arg1) {
            return arg0.start - arg1.start;
        }
	   
	}
	
	private static void testInheritance() {
	    B b = new B();
	}
	
	private static void testGeneric() {
	    List<Boolean> l = new ArrayList<Boolean>();
	    l.add(false);
	    foo(l);
	}
	
	private static void foo(List<? extends Object> x) {
	    for (int i = 0; i < x.size(); i++) {
	        System.out.println(x.get(0));
	        out.println(x.get(0).getClass());
	    }
	    
	}
	
	private static void testStrings() {
	    StringBuffer a =  new StringBuffer();
        a.append(true);
        a.append(3.0e9);
        a.append("abc");
        out.println(a);
        
        B a1 = new B();
	}
}

class A {
    A() {
        System.out.println("A");
    }
}

class B extends A implements Cloneable{
    B() {
        super();
        System.out.println("B");
    }
}