/**
 4
 a 0
 b 2
 c 0
 d 0
 */
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
public class CF141C {
    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = new PrintWriter(System.out);// It seems PrintWriter has better performance than PrintStream.
        solve();
        out.close();
    }
    
    private static class Item implements Comparable<Item> {
        private String name_;
        private int idx_;
        private int h;
        
        public Item(String name, int idx, int height) {
            name_ = name;
            idx_ = idx;
            h = height;
        }
        @Override
        public int compareTo(Item o) {
            // TODO Auto-generated method stub
            return idx_  - o.idx_;
        }
        
        
    }
    private static void solve() throws IOException {
        int n = in.nextInt();
        Item[] a = new Item[n];
        for (int i = 0; i < n; i++) {
            String name = in.next();
            int idx = in.nextInt();
            a[i] = new Item(name, idx, 1);
        }
        
        Arrays.sort(a);
        for (int i = 0; i < n; i++) {
            if (a[i].idx_ > i) {
                out.println(-1);
                return;
            }
            
            int k = i - a[i].idx_;
            for (int j = 0; j < i; j++) {
                if (a[j].h >= k) {
                    a[j].h++;
                }
            }
            a[i].h = k;
        }
        
        for (int i = 0; i < n; i++) 
            out.println(a[i].name_ + " " + (a[i].h+1));
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
