package com.YJH.west2.q.chongwudianself;

import java.time.LocalDate;

public class test {
    public static void main(String[] args) {

        // ����һ�������ʵ��
        MyAnimalShop shop = new MyAnimalShop(88888);

        ChineseDog dog1 = new ChineseDog("С����", 1,0, true);
        ChineseDog dog2 = new ChineseDog("���ƹ�", 2, 1, false);
        ChineseDog dog3 = new ChineseDog("��ʿ��", 3, 0, true);
        ChineseDog dog4 = new ChineseDog("��Ȯ", 4, 1, false);

        Cat cat1 = new Cat("��ķè", 1, 0);
        Cat cat2 = new Cat("С��è", 2, 1);

        Rabbit rabbit1 = new Rabbit("����", 1, 0);

        try {
            shop.buyAnimal(dog1);
            shop.buyAnimal(dog2);
            shop.buyAnimal(dog3);
            shop.buyAnimal(cat1);
            shop.buyAnimal(cat2);
            shop.buyAnimal(rabbit1);
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }

        shop.setBalance(10.0);

        // ��������
        try {
            shop.buyAnimal(dog4);
        } catch (InsufficientBalanceException ex) {
            System.out.println(ex.getMessage());
        }

        // �����д��ͻ�
        Customer customer1 = new Customer("����", 1, LocalDate.of(2024, 10, 23));
        Customer customer2 = new Customer("����", 2, LocalDate.of(2024, 10, 23));
        Customer customer3 = new Customer("����", 3, LocalDate.of(2024, 10, 23));
        Customer customer4 = new Customer("����", 4, LocalDate.of(2024, 10, 23));

        // ����Ӫҵ
        try {
            shop.greetCustomer(customer1, dog1);
            shop.greetCustomer(customer1, dog2);
            shop.greetCustomer(customer2, cat1);
            shop.greetCustomer(customer2, cat2);
            shop.greetCustomer(customer3, rabbit1);
            shop.greetCustomer(customer4, dog1);
            shop.greetCustomer(customer4, dog3);
        } catch (AnimalNotFoundException ex) {
            System.out.println(ex.getMessage());
        }


        // ���Թص�
        shop.close();
        try {
            shop.greetCustomer(customer4, dog3);
        } catch (RuntimeException ex) {
            System.out.println(ex.getMessage());
        }

        // ����Ӫҵ
        shop.open();
        try {
            shop.buyAnimal(dog4);
            shop.greetCustomer(customer1, dog4);
            shop.greetCustomer(customer1, dog4);
        } catch (InsufficientBalanceException | AnimalNotFoundException ex) {
            System.out.println(ex.getMessage());
        }
        shop.close();
    }
}
