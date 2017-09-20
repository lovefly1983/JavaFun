package fun.generic;


/**
 * @author lovefly1983
*/
public class Pig extends Animal<Vegetable> {

    public Pig() {
        this(new Vegetable());
    }

    public Pig(Vegetable vegetable) {
        super(vegetable);
    }
}