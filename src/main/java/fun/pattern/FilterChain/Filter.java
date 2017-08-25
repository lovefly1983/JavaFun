package fun.pattern.FilterChain;

/**
 * Created by chunjiewang on 8/25/17.
 */
public interface Filter {
    default void preFilter() {
        System.out.println("pre filter - default");

    }

    void doFilter(String request, FilterChain filterChain);

    default void postFilter() {
        System.out.println("post filter - default");
    }
}
