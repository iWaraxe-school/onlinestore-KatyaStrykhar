package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

abstract public class Category {
    protected String name;
    protected final List<Product> productList;

    public Category() {
        productList = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    @Override
    public String toString() {
        return name;
    }
}
