package by.issoft.store;

/*
тут через refliction надо считывать категории, какие существуют (субклассы Category), сгруппировать их поля productList
 в коллекцию и оформить красивый вывод на экран (создать метод print, который будет вызывать принты для productList
 каждой категории)
*/

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;


public class Store {
    private List<Class> categories;
    private List<List<Product>> bigProdList;

    public Store() {
        categories = new ArrayList<Class>();
        bigProdList = new ArrayList<List<Product>>();
        this.createListCategories();
        this.createListProductLists();
    }

    public void createListCategories(){
        Reflections reflection = new Reflections(Category.class, new SubTypesScanner());
        Set<Class<? extends Category>> subTypes = reflection.getSubTypesOf(Category.class);
        for(Class<? extends Category> type : subTypes) categories.add(type);
    }

    public void createListProductLists(){
        for ( Class type : categories){
            Reflections reflection = new Reflections(type.class);
            Method getList = type.getMethod ("getProductList");
            List<Product> temp = (List<Product>) getList.invoke();
            bigProdList.add(temp);

        }

    }

    public List<Class> getCategories() {
        return categories;
    }


}
