package fun.pattern.FilterChain;

/**
 * Created by chunjiewang on 8/25/17.
 */
public class CityFilter implements Filter {
    @Override
    public void doFilter(String request, FilterChain filterChain) {
        System.out.println("before " + getClass().getName() + "... 222 do filter");
        filterChain.doFilter(request);
        System.out.println("after " +  getClass().getName() + "... 222 do filter");
    }
}
