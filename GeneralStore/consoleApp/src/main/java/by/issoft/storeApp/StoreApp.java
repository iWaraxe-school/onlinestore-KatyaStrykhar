package by.issoft.storeApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;
//main метод. Инициирует store и распечатывает инфу по продуктам


public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);

        helper.fillStore();
        store.printStore(); // print ProductList


    }

}


