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
public class CF90B {
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
        int n = in.nextInt(), m = in.nextInt();;
        int[][] row = new int[26][n], col = new int[26][m];
        char[][] g = new char[n][m];
        String line = null;
        int c = 0;
        for (int i = 0; i < n; i++) {
            line = in.next();
            for (int j = 0; j < m; j++) {
                c = line.charAt(j) - 'a';
                row[c][i]++;
                col[c][j]++;
            }
            g[i] = line.toCharArray();
        }
        String ans = "";
        for (int i = 0; i < n; i++)
            for (int j = 0; j < m; j++) {
                c = g[i][j] - 'a';
                if (row[c][i] <= 1 && col[c][j] <= 1) ans += g[i][j];
            }
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
