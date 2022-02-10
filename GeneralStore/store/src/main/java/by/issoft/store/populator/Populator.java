package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;
import com.github.javafaker.Faker;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

abstract public class Populator {
    protected Faker faker;
    protected List<Category> subTypes;
    protected Store store;

    public Populator(Store store) {
        faker = new Faker();
        this.subTypes = new ArrayList<>();
        this.store = store;
    }

    protected String createName(String categoryName){
        String ret = null;
        switch (categoryName){
            case "milk":
                ret = faker.food().ingredient().replace("'", "");
                break;
            case "phone":
                ret = faker.animal().name().replace("'", "");
                break;
            case "bike":
                ret = faker.book().title().replace("'", "");
                break;
            default:
                throw new IllegalStateException("Unexpected value: " + categoryName);
        }
        return ret;
    }
    protected Double createRate(){ return faker.number().randomDouble(1, 1, 5);}
    protected int createPrice(){ return (int) (Math.random()*100); }

    protected void createSubTypesList(){
        Reflections reflection = new Reflections("by.issoft.domain.categories", new SubTypesScanner());
        Set<Class<? extends Category>> subTypesCategory = reflection.getSubTypesOf(Category.class);
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
    protected void generateProducts(){
        for(Category entry: subTypes){
            Random random = new Random();
            int j = (10 + random.nextInt(20));
            for(int i = 0; i< j; i++){
                String name = (this.createName(entry.getName()));
                Double rate = this.createRate();
                int price = this.createPrice();
                Product product = new Product(name, rate, price);
                entry.putProductToList(product);
            }
            store.addCategory(entry);
        }
    }

    public abstract void fillStoreWithPopulator();
}
