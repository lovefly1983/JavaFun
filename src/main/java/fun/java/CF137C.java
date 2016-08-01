package fun.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF137C {
    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = new PrintWriter(System.out);// It seems PrintWriter has better performance than PrintStream.
        solve();
        out.close();
    }
    
    private static class Interval implements Comparable<Interval> {
        private int start_, end_;
        Interval(int start, int end) {
            start_ = start;
            end_ = end;
        }
        @Override
        public int compareTo(Interval arg0) {
            // TODO Auto-generated method stub
            return start_ - arg0.start_;
        }
        
    }
    
    private static void solve() throws IOException {
        int n = in.nextInt();
        Interval[] its = new Interval[n];
        for (int i = 0; i < n; i++) {
            its[i] = new Interval(in.nextInt(), in.nextInt());
        }
        Arrays.sort(its);
        int start = its[0].start_, end = its[0].end_;
        int ans = 0;
        for (int i = 1; i < its.length; i++) {
            if (its[i].start_ > start && its[i].end_ < end) ans++;
            start = Math.max(its[i].start_, start);
            end = Math.max(its[i].end_, end);
        }
        out.println(ans);
    }

    private static class FastScanner {
        BufferedReader br;
        StringTokenizer st;

        public FastScanner(InputStream is) {
            br = new BufferedReader(new InputStreamReader(is));
        }

        public boolean hasNext() {
            while (st == null || !st.hasMoreTokens()) {
                try {
                    String line = br.readLine();
                    if (line == null) {
                        return false;
                    }
                    st = new StringTokenizer(line);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (st != null && st.hasMoreTokens()) {
                return true;
            }
            return false;
        }

        public String next() {
            if (hasNext()) {
                return st.nextToken();
            }
            return null;
        }

        public int nextInt() {
            return Integer.parseInt(next());
        }

        public long nextLong() {
            return Long.parseLong(next());
        }

        public double nextDouble() {
            return Double.parseDouble(next());
        }
    }
}
