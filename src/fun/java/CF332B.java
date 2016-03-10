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
public class CF332B {
    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = new PrintWriter(System.out);// It seems PrintWriter has better performance than PrintStream.
        solve();
        out.close();
    }
    
    private static void solve() throws IOException {
        int n = in.nextInt(), k = in.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) a[i] = in.nextInt();
        long[] s = new long[n];
        
        long sum = 0;
        for (int i = 0; i < k; i++) sum += a[i];
        s[0] = sum;
        for (int i = 1; i < n-k+1; i++) s[i] = s[i-1] - a[i-1] + a[i+k-1];
        
        int[] b = new int[n];
        b[n-k] = n-k;
        for (int i = n-k-1; i >= 0; i--) {
            b[i] = i;
            if (s[i] < s[b[i+1]]) b[i] = b[i+1];
        }
        
        int x = -1, y = -1;
        long ans = Long.MIN_VALUE;
        for (int i = 0; i < n-2*k+1; i++) {
            if (ans < s[i] + s[b[i+k]]) {
                ans = s[i] + s[b[i+k]];
                x = i+1;
                y = b[i+k]+1;
            }
        }
        out.println(x + " " + y);
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
