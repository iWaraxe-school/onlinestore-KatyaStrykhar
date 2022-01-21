package by.issoft.storeApp;

import by.issoft.domain.categories.BikeCategory;
import by.issoft.domain.categories.MilkCategory;
import by.issoft.domain.categories.PhoneCategory;
import by.issoft.store.Store;
//main метод. Инициирует store и распечатывает инфу по продуктам


public class StoreApp {
    public static void main(String[] args) {
        // если не в StoreApp создавать экземпляры классов категорий, то где?
        MilkCategory milk = new MilkCategory();
        PhoneCategory phone = new PhoneCategory();
        BikeCategory bike = new BikeCategory();

        Store store = new Store();
        store.printStore();




    }



}
