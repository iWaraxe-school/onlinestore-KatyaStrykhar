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
    private final List<Class> categories;
    private final List<List<Product>> bigProdList;

    public Store() {
        categories = new ArrayList<Class>();
        bigProdList = new ArrayList<List<Product>>();
        this.createListCategories();
        this.createListProductLists();
    }

    private void createListCategories(){
        Reflections reflection = new Reflections(Category.getClass(), new SubTypesScanner()); // не могу сообразить, каким указать первый аргумент
        Set<Class<? extends Category>> subTypes = reflection.getSubTypesOf(Category.class);
        categories.addAll(subTypes);
    }

    private void createListProductLists(){
        for ( Class type : categories){
            /* Reflections reflection = new Reflections(type.getClass());
            НУЖНА ЭТА СТРОЧКА? */
            Method getList = null;
            try {
                getList = type.getMethod ("getProductList");
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
            List<Product> temp = null;
            if (getList != null)
                temp = (List<Product>) getList.invoke();
                // Здесь пишет ошибку. Если правильно поняла, то идее не нравятся скобки
            bigProdList.add(temp);

        }

    }

    public List<Class> getCategories() {
        return categories;
    }

    public List<List<Product>> getBigProdList() {
        return bigProdList;
    }
}
