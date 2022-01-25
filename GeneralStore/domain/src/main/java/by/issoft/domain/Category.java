package by.issoft.domain;

import java.util.ArrayList;
import java.util.List;

abstract public class Category {
    protected String name;
    protected List<Product> productList;

    public Category(String name) {
        this.name = name;
        productList = new ArrayList<Product>();
    }

    public String getName() {
        return name;
    }

    public List<Product> getProductList() {
        return productList;
    }

    public void putProductToList (Product product){
        productList.add(product);
    }

}
