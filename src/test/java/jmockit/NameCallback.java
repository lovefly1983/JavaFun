package jmockit;

/**
 * @author cwang
 */
public class NameCallback extends Callback {
    private String name;

    public NameCallback(String name) {
        super(name);
    }

    public String getName() {
        return name;
    }
}
