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
import java.util.List;
import java.util.Scanner;
import java.util.StringTokenizer;

public class CF327D {
    private static FastScanner in;
    private static PrintStream out;

    private static int n, m, cx, cy, cnt;
    private static char[][] g = null;
    private static boolean[][] vis;
    private static int[] dx = new int[]{0, 0, 1, -1};
    private static int[] dy = new int[]{1, -1, 0, 0};
    private static char[] a = null;
    private static int[] b = null, c = null;
    public static void main(String[] args) throws IOException {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = System.out;
        solve();
        out.close();
    }
    
    private static void dfs(int i, int j) {
        vis[i][j] = true;
        a[cnt] = 'B';
        b[cnt] = i+1;
        c[cnt] = j+1;
        cnt++;
        for (int k = 0; k < 4; k++) {
            int x = i + dx[k], y = j + dy[k];
            if (x >= 0 && x < n && y >= 0 && y < m && !vis[x][y] && g[x][y] == '.') {
                dfs(x, y);
            }
        }
        if (i != cx || j != cy) {
            a[cnt] = 'D';
            b[cnt] = i+1;
            c[cnt] = j+1;
            cnt++;
            a[cnt] = 'R';
            b[cnt] = i+1;
            c[cnt] = j+1;
            cnt++;
        }
       
    }
    
    private static void solve() throws IOException {
        n = in.nextInt();
        m = in.nextInt();
        g = new char[n][m];
        vis = new boolean[n][m];
        cnt = 0;
        a = new char[1000001];
        b = new int[1000001];
        c = new int[1000001];
        
        for (int i = 0; i < n; i++) 
            g[i] = in.next().toCharArray();
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (vis[i][j] == false && g[i][j] == '.') {
                    cx = i;
                    cy = j;
                    dfs(i, j);
                }
            }
        }
      
        out.println(cnt);
        for (int i = 0; i < cnt; i++) {
            out.println(a[i] + " " + b[i] + " " + c[i]);
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
