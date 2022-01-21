package by.issoft.domain;

import by.issoft.domain.categories.MilkCategory;

public class Product {
    private final Category name;
    private final double rate;
    private final int price;


    private Product(Category name, double rate, int price) {
        this.name = name;
        this.rate = rate;
        this.price = price;
        setToProductList();
    }

    public static Product createProduct(Category name, double rate, int price) {
        if ((rate > 0)&(price > 0)) return new Product(name, rate, price);
        else return new Product(name, 0.0, 0); // нужна ли эта проверка?
    }

    private void setToProductList() {
        //здесь продукт добавляется в productList нужной категории...... пока сырой код без рефлексии:
        if (name instanceof MilkCategory)


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
