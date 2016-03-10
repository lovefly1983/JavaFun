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
public class CF24A {
    private static FastScanner in;
    private static PrintWriter out;
    private static int n = 0;
    private static int sum = 0;
    private static int[][] g = null;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = new PrintWriter(System.out);// It seems PrintWriter has better performance than PrintStream.
        solve();
        out.close();
    }
    
    private static void dfs(int i, int p) {
        if (i == 1) return;
        for (int j = 1; j <= n; j++) {
            if (g[i][j] != 0 && j != p) {
                if (g[i][j] < 0) sum += Math.abs(g[i][j]); 
                dfs(j, i);
                break;
            }
        }
    }
    private static void solve() throws IOException {
        n = in.nextInt();
        g = new int[n+1][n+1];
        int p0 = -1, c0 = 0;
        int p1 = -1, c1 = 0;
        for (int i = 1; i <= n; i++) {
            int u = in.nextInt(), v = in.nextInt(), cost = in.nextInt();
            g[u][v] = cost;
            g[v][u] = -cost;
            if (u == 1 || v == 1) {
                if (p0 == -1) {
                    p0 = u == 1 ? v : u;
                    c0 = u == 1 ? 0 : cost;
                }
                else{
                    p1 = u == 1 ? v : u;
                    c1 = u == 1 ? 0 : cost;
                }
            }
        }
        int ans = Integer.MAX_VALUE;
        sum = c0;
        dfs(p0, 1);
        ans = Math.min(ans,  sum);
        sum = c1;
        dfs(p1, 1);
        ans = Math.min(ans,  sum);
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
