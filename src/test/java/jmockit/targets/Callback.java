package jmockit.targets;

/**
 * @author lovefly1983
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
