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
public class CF331C {
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
        /* dp
        List<Integer> dp = new ArrayList<Integer>(10);
        int n = in.nextInt();
        
        for (int i = 0; i < 10; i++) {
            if (i == 0) dp.add(0); 
            else dp.add(1);
            if (i == n) {
                out.println(dp.get(i));
                return;
            }
        }
        
        for (int i = 10; i <= n; i++) {
            int min_cnt = Integer.MAX_VALUE;
            int ii = i;
            int k = 0;
            while (ii > 0) {
                k = ii % 10;
                if (k != 0)
                    min_cnt = Math.min(min_cnt, dp.get(10-k) + 1);
                ii /= 10;
            }
            dp.add(min_cnt);
            dp.remove(0);
            if (i == n) break;
        }
        out.println(dp.get(dp.size()-1));
        */ 
        // greedy
        int n = in.nextInt();
        int k = 0;
        while (n > 0) {
            int max_n = 0;
            int x = n;
            while (x > 0) {
                max_n = Math.max(x % 10, max_n);
                x /= 10;
            }
            n -= max_n;
            k++;
        }
        out.println(k);
        
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
