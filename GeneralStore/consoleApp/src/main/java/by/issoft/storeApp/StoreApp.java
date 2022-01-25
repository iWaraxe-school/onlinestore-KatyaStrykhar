package by.issoft.storeApp;

import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);

        helper.fillStore();
        store.printStore();


    }

}


