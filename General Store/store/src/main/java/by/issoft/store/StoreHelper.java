package by.issoft.store;

import by.issoft.domain.Category;
import org.reflections.Reflections;
import org.reflections.scanners.SubTypesScanner;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/* create List<Class> helper.categories with reflection */

public class StoreHelper {
    private final List<Class> categories;


    public StoreHelper() {
        categories = new ArrayList<Class>();
        this.createListCategories();
    }

    public List<Class> getCategories() {
        return categories;
    }

    private void createListCategories(){
        Reflections reflection = new Reflections(Category.getClass(), new SubTypesScanner()); // не могу сообразить, каким указать первый аргумент
        Set<Class<? extends Category>> subTypes = reflection.getSubTypesOf(Category.class);
        categories.addAll(subTypes);
    }
}
