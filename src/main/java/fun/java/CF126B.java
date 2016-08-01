package fun.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF126B {
    private static FastScanner in;
    private static PrintStream out;
    private static Set<Integer> st = null;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = System.out;
        solve();
        out.close();
    }
    
    private static int[] getNext(String p)
    {
        int len = p.length();
        int i,j;
        i = 0; j = -1;
        int[] next = new int[len];
        next[0] = -1;
        for (i = 1; i < len; ++i)
        {
            while (j > -1 && p.charAt(j+1) != p.charAt(i))
                j = next[j];
            if (p.charAt(j+1) == p.charAt(i)) ++j;
            next[i] = j;
        }
        st = new HashSet<Integer>();
        for (i = 0; i < len-1; i++) {
            if (next[i] > -1)
                st.add(next[i]);
        }
        return next;
    }
    
    private static void solve() throws IOException {
        String s = in.next();
        int[] next = getNext(s);
        int i = s.length() - 1;
        while (next[i] != -1) {
            if (st.contains(next[i])) {
                out.println(s.substring(0, next[i]+1));
                return;
            }
            i = next[i];
        }
        out.println("Just a legend");
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
