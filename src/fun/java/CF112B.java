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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF112B {
    private static FastScanner in;
    private static PrintStream out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = System.out;
        solve();
        out.close();
    }
    
    private static void solve() throws IOException {
        int n = in.nextInt() / 2;
        int x = in.nextInt(), y = in.nextInt();
        if ((x == n || x == n+1) && (y == n || y == n+1)) 
            out.println("NO");
        else
            out.println("YES");
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
