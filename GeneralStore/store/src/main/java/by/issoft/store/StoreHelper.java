package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.populator.Populator;
import by.issoft.store.populator.RandomStorePopulator;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StoreHelper {
    private List<Category> subTypes;
    private Store store;


    public StoreHelper(Store store) {
        subTypes = new ArrayList<Category>();
        this.store = store;
    }

    public void fillStore(){
        Populator populator = new RandomStorePopulator();
        Random random = new Random();
        this.createProductListToAdd();
        for(Category entry: subTypes){
            int j = (10 + random.nextInt(20));
            for(int i = 0; i< j; i++){
                String name = (populator.createName(entry.getName()) + " [" + entry.getName() + "]");
                Double rate = populator.createRate();
                int price = populator.createPrice();
                Product product = new Product(name, rate, price);
                entry.putProductToList(product);
            }
            store.addCategory(entry);
        }
    }

    private void createProductListToAdd(){
        Reflections reflection = new Reflections("by.issoft.domain.categories", new SubTypesScanner());
        Set <Class<? extends Category>> subTypesCategory = reflection.getSubTypesOf(Category.class);
        for (Class<? extends Category> aClass : subTypesCategory) {
            try {
                subTypes.add(aClass.getConstructor().newInstance());
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
