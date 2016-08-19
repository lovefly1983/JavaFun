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
public class CF91A {
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
        String s = in.next(), p = in.next();
        
        boolean f[] = new boolean[26];
        for (char c: s.toCharArray()) f[c-'a'] = true;
        boolean g[] = new boolean[26];
        for (char c: p.toCharArray()) g[c-'a'] = true;
        for (int i = 0;i < 26; i++)
            if (g[i] && !f[i]) {
                System.out.print(-1);
                return;
            }
        
        /* it will TLE ... so we have to do some optimization (see the next[][26] array as follows)
        int ans = 0;
        int prev = -1, j = 0;
        for (int i = 0; i < p.length(); i++) {
            if (prev == s.length() - 1) ans++;
            int start = (prev + 1) % s.length();
            j = start;
            // to the end
            while (j < s.length() && s.charAt(j) != p.charAt(i)) j++;
            if (j == s.length()) {
                ans++;
                // from the begin
                j = 0;
                while (j !=  start && s.charAt(j) != p.charAt(i)) j++;
                if (j == start) {
                    out.println(-1);
                    return;
                }
            }
            prev = j;
        }
        out.println(ans+1);*/
        
        int n = s.length();
        int next[][] = new int[n + 1][26]; // next[i][j]: from i to end, the first index that have character 'a'+j
        
        for (int i = 0;i < 26; i++) 
            next[n][i] = -1;
        
        for (int i = n - 1; i >= 0; i--) {
            for (int j = 0; j < 26; j++)
                next[i][j] = next[i+1][j];
            next[i][s.charAt(i)-'a'] = i;
        }
        
        int ans = 1, j = 0;
        for (int i = 0; i < p.length(); i++){
            int c = p.charAt(i) - 'a';
            if (next[j][c] == -1){
                j = 0;
                ans++;
            }
            j = next[j][c] + 1;
        }
        System.out.print(ans);
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
