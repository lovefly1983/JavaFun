package fun.java;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;
import java.util.StringTokenizer;


public class CF315B {
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
    	int m = in.nextInt();
    	long add = 0;
    	long a[] = new long[n+1];
    	for (int i = 1; i <= n; i++) {a[i] = in.nextLong();}
    	int x, y, z;
    	for (int i = 0; i < m; i++) {
    		x = in.nextInt();
    		y = in.nextInt();
    		if (x == 1) {
    			z = in.nextInt();
    			a[y] = z-add;
    		}
    		else if (x == 2) {
    			add += y;
    		}
    		else {
    			out.println(a[y]+add);
    		}
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
