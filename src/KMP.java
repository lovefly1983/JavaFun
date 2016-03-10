
public class KMP {
    private static java.io.PrintStream out = System.out;
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] next = getNext("abcabcd");
        for (int x : next) {
            out.print(x + " ");
        }
        out.println();
        find("a", "a");
        find("a", "b");
        find("abc", "ab");
        find("abcddabcd", "abc");
        find("abcddabcd", "bcd");
    }

    /**
     * 1. get the next array
     * 2. find the match j for specific i and check j == p.length() - 1
     * 3. if a match is found, continue for the next match (j = next[j])
     * @param s
     * @param p
     * @return
     */
    private static int find(String s, String p) {
        out.println(s + " : " + p);
        int j = -1;
        int[] next = getNext(p);
        for (int i = 0; i < s.length(); i++) {
            while (j > -1 && s.charAt(i) != p.charAt(j+1)) j = next[j];
            if (s.charAt(i) == p.charAt(j+1)) j++;
            if (j == p.length() - 1) {
                out.println("match at: " + (i - p.length() + 1));
                j = next[j]; // to find the next match
            }
        }
        return -1;
    }
    /**
     * @param s
     * @return
     */
    private static int[] getNext(String s)
    {
        int len = s.length();
        int i,j;
        i = 0; j = -1;
        int[] next = new int[len];
        next[0] = -1;
        for (i = 1; i < len; ++i)
        {
            while (j > -1 && s.charAt(j+1) != s.charAt(i))
                j = next[j];
            if (s.charAt(j+1) == s.charAt(i)) ++j;
            next[i] = j;
        }
        return next;
    }
}
