package Work2;

public class Parrot extends Animal {
    public Parrot(String name, int age, String gender, double price) {
        super(name, age, gender, price);
    }


    @Override
    public String toString() {
        return "名字" + name + ",年龄" + age + ",性别" + gender + ",价格" + price;
    }
}
