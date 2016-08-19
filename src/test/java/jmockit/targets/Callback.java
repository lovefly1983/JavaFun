package jmockit.targets;

/**
 * @author cwang
 */
public class Callback {
    private String name;

    public Callback() {
    }
    public Callback(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
    public String getPriority() {
        return "High";
    }
}
