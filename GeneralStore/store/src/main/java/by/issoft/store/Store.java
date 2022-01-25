package by.issoft.store;

/*
тут через refliction надо считывать категории, какие существуют (субклассы Category), сгруппировать их поля productList
 в коллекцию и оформить красивый вывод на экран (создать метод print, который будет вызывать принты для productList
 каждой категории)
*/

import by.issoft.domain.Product;
import org.jetbrains.annotations.NotNull;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class Store {
    private final List<Class> categories;
    private final List<List<Product>> bigProdList;

    public Store(@NotNull StoreHelper helper) {
        categories = helper.getCategories();
        bigProdList = new ArrayList<List<Product>>();
        this.createListProductLists();
    }

    public void createListProductLists(){
        for ( Class type : categories){
            try {
                Method getList = type.getDeclaredMethod("getProductList");
                List<Product> temp = (List<Product>) getList.invoke(type);
                bigProdList.add(temp);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }

        }
    }

    public List<Class> getCategories() {
        return categories;
    }

    public List<List<Product>> getBigProdList() {
        return bigProdList;
    }

    public void printStore(){
        for (List<Product> temp : bigProdList){
            for (Product tempProd : temp){
                System.out.println(tempProd.toString());
            }
        }

    }
}
