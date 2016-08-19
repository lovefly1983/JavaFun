package fun.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF118B {
    private static FastScanner in;
    private static PrintStream out;

    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = System.out;
        solve();
        out.close();
    }
    
    private static String getS(int n) {
        String ans = "0";
        int i = 0;
        for (i = 1; i < n; i++) {
            ans = ans + " " + i;
        }
        for (i = n; i >= (n == 0 ? 1 : 0); i--) {
            ans = ans + " " + i;
        }
        return ans;
    }
    
    private static void solve() throws IOException {
        int n = in.nextInt();
        String[] p = new String[] {
            "0",
            "0 1 0",
            "0 1 2 1 0",
            "0 1 2 3 2 1 0",
            "0 1 2 3 4 3 2 1 0",
            "0 1 2 3 4 5 4 3 2 1 0",
            "0 1 2 3 4 5 6 5 4 3 2 1 0",
            "0 1 2 3 4 5 6 7 6 5 4 3 2 1 0",
            "0 1 2 3 4 5 6 7 8 7 6 5 4 3 2 1 0",
            "0 1 2 3 4 5 6 7 8 9 8 7 6 5 4 3 2 1 0"
        };
        
        int k = 0;
        for (int i = 0; i < 2 * n + 1; i++) {
            if (i > n) k = 2*n-i;
            else k = i;
            for (int j = 0; j < 2*n - 2*(k%(n+1)); j++)
                out.print(' ');
            out.println(p[k%(n+1)]);
            //out.println(getS(k%(n+1)));
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
