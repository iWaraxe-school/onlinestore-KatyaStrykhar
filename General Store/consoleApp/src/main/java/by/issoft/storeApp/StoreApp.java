package by.issoft.storeApp;

import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import by.issoft.store.RandomStorePopulator;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;
//main метод. Инициирует store и распечатывает инфу по продуктам


public class StoreApp {
    public static void main(String[] args) {
        // create category class instances
        MilkCategory milk = new MilkCategory();
        PhoneCategory phone = new PhoneCategory();
        BikeCategory bike = new BikeCategory();


        RandomStorePopulator populator = new RandomStorePopulator();
        populator.toCreate(milk);//create random quantity products with faker
        populator.toCreate(phone);
        populator.toCreate(bike);


        StoreHelper helper = new StoreHelper(); // create List<Class> helper.categories with reflection
        Store store = new Store(helper); // create ProductList

        store.printStore(); // print ProductList




    }



}
