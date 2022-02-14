package by.issoft.store;

import by.issoft.domain.Product;
import by.issoft.store.http.HttpClient;
import by.issoft.store.populator.DBPopulator;
import by.issoft.store.populator.Populator;
import by.issoft.store.populator.RandomStorePopulator;

import java.util.List;

public class StoreHelper {
    private Store store;
    Populator populator;
    HttpClient client;


    public StoreHelper(Store store) {this.store = store;}

    public void fillStore(boolean useDB){
        if (useDB) {populator = new DBPopulator(store);}
        else {populator = new RandomStorePopulator(store);}
        populator.fillStoreWithPopulator();
    }

    public void fillStore(HttpClient client){
        this.client = client;
        List<Product> products = client.getListOfProducts(store);
    }

}
