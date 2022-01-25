package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import org.reflections.Reflections;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class StoreHelper {
    private List<Category> sybTypes;
    private Store store;


    public StoreHelper(Store store) {
        sybTypes = new ArrayList<Category>();
        this.store = store;
    }

    public void fillStore(){
        RandomStorePopulator populator = new RandomStorePopulator();
        Random random = new Random();
        this.createProductListToAdd();
        for(Category entry: sybTypes){
            int j = random.nextInt(10);
            for(int i = 0; i< j; i++){
                String name = populator.toCreateName(entry.getName());
                Double rate = populator.toCreateRate();
                int price = populator.toCreatePrice();
                Product product = new Product(name, rate, price);
                entry.putProductToList(product);
            }
            store.addCategory(entry);
        }
    }

    private void createProductListToAdd(){
        Reflections reflection = new Reflections();
        for (Class<? extends Category> aClass : reflection.getSubTypesOf(Category.class)) {
            try {
                sybTypes.add(aClass.getConstructor().newInstance());
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            }
        }
    }

}
