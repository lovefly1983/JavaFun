package fun.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.HashMap;
import java.util.Map;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF291A {
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
        int n = in.nextInt();
        Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
        int ans = 0; 
        for (int i = 0; i < n; i++) {
            int x = in.nextInt();
            if (x != 0) {
                if (mp.containsKey(x)) mp.put(x, mp.get(x)+1);
                else mp.put(x, 1);
                if (mp.get(x) >= 3) {
                    out.println("-1");
                    return;
                }
            }
        }
        for (Integer x: mp.values()) {
            if (x == 2) ans++;
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
