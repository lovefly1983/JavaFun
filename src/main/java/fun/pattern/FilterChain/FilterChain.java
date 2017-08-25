package fun.pattern.FilterChain;

import java.util.ArrayList;
import java.util.List;

/**
 * Note: NotThreadSafe
 *
 * Created by chunjiewang on 8/25/17.
 */
public class FilterChain  {
    private List<Filter> filters = new ArrayList();
    private int cnt = 0;
    private PlayService playService;

    public FilterChain(PlayService playService) {
        this.playService = playService;
    }

    public void addFilter(Filter filter) {
        filters.add(filter);
    }

    public void doFilter(String request) {
        if (cnt < filters.size()) {
            filters.get(cnt++).doFilter(request, this);
        } else {
            playService.handle(request);
        }
    }

}
