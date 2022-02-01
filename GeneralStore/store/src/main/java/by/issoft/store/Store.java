package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.sorting.SortStore;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class Store {
    private List<Category> categoryList;
    private List<Product> listOfAllProducts;
    private SortStore sortMap;

    public Store() {
        categoryList = new ArrayList<>();
        listOfAllProducts = new ArrayList<>();
        sortMap = new SortStore();
    }

    public List<Product> getListOfAllProducts() {
        return listOfAllProducts;
    }

    public void printListOfProducts(){
        listOfAllProducts.stream()
                .forEach(Product::toPrint);
    }

    public void addCategory (Category category){
        categoryList.add(category);
    }

    public void fillAndPrintStore(){
        for (var temp : categoryList){
            System.out.println("Категория: " + temp.getName());
            listOfAllProducts.addAll(temp.getProductList());
            for(Product prod: temp.getProductList()){
                prod.toPrint();
            }
        }
    }

    public void printTop() {
        Comparator<Product> comp = (a, b) -> b.getPrice() - a.getPrice();
        this.getListOfAllProducts().stream()
                .sorted(comp)
                .limit(5)
                .forEach(System.out::println);
    }

    public void toSort() {
        sortMap.toSort(listOfAllProducts);
    }
}
