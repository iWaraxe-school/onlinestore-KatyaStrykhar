package by.issoft.store;

import by.issoft.domain.Category;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private List<Category> categoryList;

    public Store() {categoryList = new ArrayList<>();}

    public void addCategory (Category category){
        categoryList.add(category);
    }

    public void printStore(){
        for (var temp : categoryList){
            System.out.println("Категория: " + temp.getName());
            for(var prod: temp.getProductList()){
                prod.toPrint();
            }
        }
    }
}
