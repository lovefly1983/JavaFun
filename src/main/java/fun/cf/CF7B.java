package fun.cf;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.StringTokenizer;

/*
 * This is the default template for Java 
 * */
public class CF7B {
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
        int countOperation = in.nextInt(), sizeOfMemory = in.nextInt();
        int memory[] = new int[sizeOfMemory];
        boolean usedId[] = new boolean[10000];
        int tekId=0;
        Arrays.fill(memory, 0);
        
        for (int i = 0;i < countOperation; i++){
            String nameOfOperation = in.next();
            if (nameOfOperation.equals("defragment")){
                for (int j = 0;j < sizeOfMemory; j++)
                    if (memory[j]==0)
                        for (int k=j+1;k<sizeOfMemory;k++)
                            if (memory[k]!=0){
                                memory[j]=memory[k];
                                memory[k]=0;
                                break;
                            }
            }
            else if (nameOfOperation.equals("alloc")){
                int countBytes = in.nextInt();
                int found = -1;
                for (int j=0;j<sizeOfMemory-countBytes+1;j++){
                    boolean free = true;
                    for (int k=j;k<=j+countBytes-1;k++)
                        if (memory[k]!=0){
                            free=false;
                            break;
                        }
                    if (free){
                        found = j;
                        break;
                    }
                }
                if (found==-1)
                    out.println("NULL");
                else{
                    int ans = ++tekId;
                    usedId[ans]=true;
                    for (int j=found;j<=found+countBytes-1;j++)
                        memory[j]=ans;
                    out.println(ans);
                }
            }
            else{
                int delId = in.nextInt();
                if (delId<=0 || delId>tekId || !usedId[delId])
                    out.println("ILLEGAL_ERASE_ARGUMENT");
                else{
                    usedId[delId]=false;
                    for (int j=0;j<sizeOfMemory;j++)
                        if (memory[j]==delId)
                            memory[j]=0;
                }
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
