package fun;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class SolutionA {
    private static FastScanner in;
    private static PrintWriter out;

    public int maxNumOfPerformer(int[] h, int[] w) {
        Arrays.sort(w);
        Arrays.sort(h);
        int i = 0;
        int j = 0;
        while (i < h.length && j < w.length) {
            if (h[i] <= w[j]) i++;
            j++;
        }
        return i;
    }

    public static void main(String[] args) {
        InputStream inputStream = System.in;
        in = new FastScanner(inputStream);
        out = new PrintWriter(System.out);

        int n, m;
        n = in.nextInt();
        int[] h = new int[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextInt();
        }

        m = in.nextInt();
        int[] w = new int[m];
        for (int i = 0; i < m; i++) {
            w[i] = in.nextInt();
        }

        SolutionA sa = new SolutionA();
        out.print(sa.maxNumOfPerformer(h, w));
        out.close();
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


