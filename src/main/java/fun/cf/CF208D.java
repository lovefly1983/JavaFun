package fun.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF208D {
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
        int n = in.nextInt();
        long[] p = new long[n];
        long[] a = new long[5];
        long[] b = new long[5];
        long ans = 0;
        for (int i = 0; i < n; i++)
            p[i] = in.nextInt();
        for (int i = 0; i < 5; i++)
            a[i] = in.nextInt();
        for (int i = 0; i < n; i++) {
            ans += p[i];
            for (int j = 4; j >= 0; j--) {
                long k = ans / a[j];
                b[j] += k;
                ans %= a[j];
            }
        }
        for (long x : b)
            out.print(x + " ");
        out.println();
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
