package fun.pattern.FilterChain;

/**
 * Created by chunjiewang on 8/25/17.
 */
public class TestFilter {
    public static void main(String[] args) {
        FilterChain filterChain = new FilterChain(new PlayService());
        filterChain.addFilter(new NameFilter());
        filterChain.addFilter(new CityFilter());
        filterChain.doFilter("hello world");
    }
}
