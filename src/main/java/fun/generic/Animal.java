package fun.generic;

/**
 * @author lovefly1983
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
