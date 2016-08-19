package fun.gen;


/**
 * @author cwang
*/
public class Pig extends Animal<Vegetable> {

    public Pig() {
        this(new Vegetable());
    }

    public Pig(Vegetable vegetable) {
        super(vegetable);
    }
}