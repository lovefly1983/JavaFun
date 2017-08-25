package fun.annotation;

/**
 * Created by chunjiewang on 9/16/16.
 */
@Description(value = "desc")
public class Item {
    @Cacheable(name = "test cacheable", value = true)
    public void findIt() {
        System.out.println("Find it...");
    }

}