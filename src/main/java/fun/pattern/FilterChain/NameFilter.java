package fun.pattern.FilterChain;

/**
 * Created by chunjiewang on 8/25/17.
 */
public class NameFilter implements Filter {
    @Override
    public void doFilter(String request, FilterChain filterChain) {
        System.out.println(getClass().getName() + "... do filter");
        filterChain.doFilter(request);
        System.out.println(getClass().getName() + "... do filter");
    }
}
