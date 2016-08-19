package fun.gen;

/**
 * @author cwang
 */
public class Animal<T extends Food> {
    T food;

    public Animal(T food) {
        this.food = food;
    }
    public void eat() {
        System.out.println("I'm eating " + food);
    }
}
