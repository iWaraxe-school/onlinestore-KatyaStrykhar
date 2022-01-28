package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;
    private List<Product> listOfAllProducts;

    public Store() {
        categoryList = new ArrayList<>();
        listOfAllProducts = new ArrayList<>();
    }

    public List<Product> getListOfAllProducts() {
        return listOfAllProducts;
    }

    public void addCategory (Category category){
        categoryList.add(category);
    }

    public void fillAndPrintStore(){
        for (var temp : categoryList){
            System.out.println("Категория: " + temp.getName());
            for(var prod: temp.getProductList()){
                prod.toPrint();
                listOfAllProducts.add(prod);
            }
        }
    }
}
