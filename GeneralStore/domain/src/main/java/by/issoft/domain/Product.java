package by.issoft.domain;

public class Product {
    private final String name;
    private final double rate;
    private final int price;


    public Product(String name, double rate, int price) {
        this.name = name;
        this.rate = rate;
        this.price = price;

    }

    public String getName() {
        return name;
    }

    public double getRate() {
        return rate;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "[product: " + name + "] [rate: " + rate + "] [price: " + price + "]";
    }

    public void toprint() {
        System.out.println(this);
    }
}
