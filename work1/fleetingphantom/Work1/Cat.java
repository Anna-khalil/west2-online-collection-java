package Work1;

public class Cat extends Animal {

    public Cat(String name, int age, String sex) {
        super(name, age, sex, 200);
    }

    @Override
    public String toString() {
        return "品种:" + "猫猫"
                + "\n姓名:" + name
                + "\n年龄:" + age
                + "\n性别:" + sex
                + "\n价格:" + value;
    }
}
