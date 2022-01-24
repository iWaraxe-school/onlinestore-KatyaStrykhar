package by.issoft.store;

import by.issoft.domain.Category;
import org.reflections.Reflections;

import java.util.ArrayList;
import java.util.List;

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
        Reflections reflection = new Reflections();
        for (Class<? extends Category> aClass : reflection.getSubTypesOf(Category.class)) {
            categories.add(aClass);
        }

    }

}
