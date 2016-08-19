package fun.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF106B {
    private static FastScanner in;
    private static PrintWriter out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = new PrintWriter(System.out);// It seems PrintWriter has better performance than PrintStream.
        solve();
        out.close();
    }
    private static class Computer {
        public int a, b, c, p;
        Computer(int a_, int b_, int c_, int p_) {
            a = a_; b = b_; c = c_; p = p_;
        }
    }
    
    private static void solve() throws IOException {
        int n = in.nextInt();
        Computer[] comps = new Computer[n];
        for (int i = 0; i < n; i++) {
            comps[i] = new Computer(in.nextInt(), in.nextInt(), in.nextInt(), in.nextInt());
        }
        boolean[] used = new boolean[n];
        Arrays.fill(used, false);
        for (int i = 0; i < n; i++)
            for (int j = 0; j < n; j++) {
                if (comps[i].a < comps[j].a && comps[i].b < comps[j].b && comps[i].c < comps[j].c) {
                    used[i] = true;
                    break;
                }
            }
        int ans = Integer.MAX_VALUE;
        int idx = -1;
        for (int i = 0; i < n; i++) {
            if (!used[i]) {
                if (comps[i].p < ans) {
                    ans = comps[i].p;
                    idx = i;
                }
            }
        }
        out.println(idx+1);
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
