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
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF331A1 {
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
        Map<Integer, Integer> right = new HashMap<Integer, Integer>();
        int n = in.nextInt();
        int[] a = new int[n];
        long[] sum = new long[n];
        a[0] = in.nextInt();
        sum[0] = (a[0] > 0 ? a[0] : 0);
        for (int i = 1; i < n; i++) {
            a[i] = in.nextInt();
            right.put(a[i], i);
            sum[i] = (a[i] > 0 ? sum[i-1] + a[i] : sum[i-1]);
        }
        
        long ans = Long.MIN_VALUE; 
        int start = -1, end = -1;
        for (int i = 0; i < n; i++) {
            if (right.containsKey(a[i])) {
                int j = right.get(a[i]);
                if (j > i) {
                    long total = sum[j-1] - sum[i] + a[i] + a[j];
                    if (total > ans) {
                        start = i;
                        end = j;
                        ans = total;
                    }   
                }
            }
        }
        int k = 0;
        int i = start+1, j = end;
        while (i < j) {
            if (a[i] < 0) k++;
            i++;
        }
        k += (start + n - 1 - end);
        out.println(ans + " " + k);
        for (i = 0; i < n; i++) {
            if (i < start || (i > start && i < end && a[i] < 0) || (i > end))
                out.print(i+1+" ");
        }
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
