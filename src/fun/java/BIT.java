package fun.java;

/**
 * 
 * BIT in Java
 *
 */
public class BIT {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        int[] x = {1, 0, 2, 1, 1, 3, 0, 4, 2, 5};
        BIT bit = new BIT(x);
        for (int i = 1; i <= x.length; i++) {
            System.out.println(bit.getSum(i));
        }
    }

    
    /**
     * array from [1...n]
     * @param a
     */
    public BIT(int[] a) {
        n = a.length;
        c = new int[n+1];
        init(a);
    }
    
    public void update(int i, int v) {
        while (i <= n) {
            c[i] += v;
            i += lowBit(i);
        }
    }
    
    public int getSum(int i) {
        int ans = 0;
        while (i > 0) {
            ans += c[i];
            i -= lowBit(i);
        }
        return ans;
    }
    
    private void init(int x[]) {
        for (int i = 0; i < x.length; i++) {
            update(i+1, x[i]);
        }
    }
    
    private int lowBit(int n) {
        return n&(-n);
    }
    
    private int c[] = null;
    private int n;
    

}
