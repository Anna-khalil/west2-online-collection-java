package com.YJH.west2.q.chongwudianself;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class MyAnimalShop implements AnimalShop{
    private double balance;//���
    private double profit;//����
    private List<Animal> AnimalList;
    private List<Customer> CustomerList;
    private boolean isOpen;


    public MyAnimalShop(double balance) {
        this.balance = balance;
        this.profit = 0;
        this.AnimalList = new ArrayList<>();
        this.CustomerList = new ArrayList<>();
        this.isOpen = true;
    }

    @Override
    public void buyAnimal(Animal animal) throws InsufficientBalanceException{
        if (this.balance < animal.getBuyingprice()) {
            throw new InsufficientBalanceException(
                    "����, �޷�������; ��ǰ���: " + this.balance + ", ����۸�: " + animal.getBuyingprice()
            );
        }
        // ����㹻, �۳����
        this.balance -= animal.getBuyingprice();
        // ��Ӷ���
        this.AnimalList.add(animal);
        System.out.println("���̵깺���ˡ� " + animal);
        System.out.println("����ǰ��  " + this.balance);

    }

    @Override
    public void greetCustomer(Customer customer, Animal animal) throws AnimalNotFoundException, IllegalStateException{
        if (this.isOpen==false) {
            System.out.println("�̵���Ъҵ, �޷��д��ͻ�");
            throw new IllegalStateException("�̵���Ъҵ");
        }


        if (AnimalList.isEmpty()) {
            throw new AnimalNotFoundException("�����б�Ϊ��, �޷��д��ͻ�");
        }


        if (!AnimalList.contains(animal)) {
            throw new AnimalNotFoundException("�����б���û����Ϊ�� " + animal.getName() + " ���Ķ���, �޷��д��ͻ�");
        }
        // ���δ������, �������
        if (animal instanceof ChineseDog) {
            ChineseDog dog = (ChineseDog) animal;
            if (dog.isVaccineInjected==false) {
                dog.setVaccineInjected(true);
                System.out.println("Ϊ" + dog.getName() + "��������");
            }
        }


        if (!CustomerList.contains(customer)) {
            CustomerList.add(customer);
        }
        this.profit += animal.getSellingprice() - animal.getBuyingprice();
        this.balance += animal.getSellingprice();
        this.AnimalList.remove(animal);
        // ���¹˿���Ϣ
        customer.setVisitcount(customer.getVisitcount() + 1);
        customer.setLastvisitdate(LocalDate.now());

        System.out.println("���д��ͻ���  [�ͻ�]: " + customer + " ====> [������]: " + animal);
    }

    @Override
    public void close() {
        this.isOpen = false;
        // ��ӡ����
        System.out.println("���̵���Ъҵ�� ����Ϊ: " + this.profit);
        profit = 0;
    }

    @Override
    public void open() {
        this.isOpen = true;
        System.out.println(" ============ �̵��ѿ�ҵ ============ ");
        System.out.println("����ǰ���:�� " + this.balance);
        System.out.println("����ǰ�����б�:�� " + this.AnimalList);
        System.out.println("����ǰ�˿��б�:�� " + this.CustomerList);
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public double getProfit() {
        return profit;
    }

    public void setProfit(double profit) {
        this.profit = profit;
    }

    public List<Animal> getAnimalList() {
        return AnimalList;
    }

    public void setAnimalList(List<Animal> animalList) {
        this.AnimalList = animalList;
    }

    public List<Customer> getCustomerList() {
        return CustomerList;
    }

    public void setCustomerList(List<Customer> customerList) {
        this.CustomerList = customerList;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setOpen(boolean open) {
        isOpen = open;
    }
}
