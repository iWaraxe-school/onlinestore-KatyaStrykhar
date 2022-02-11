package by.issoft.store;

import by.issoft.store.populator.DBPopulator;
import by.issoft.store.populator.Populator;
import by.issoft.store.populator.RandomStorePopulator;

public class StoreHelper {
    private Store store;
    Populator populator;


    public StoreHelper(Store store) {this.store = store;}

    public void fillStore(boolean useDB){
        if (useDB) { populator = new DBPopulator(store);}
        else {populator = new RandomStorePopulator(store);}
        populator.fillStoreWithPopulator();
    }

}
