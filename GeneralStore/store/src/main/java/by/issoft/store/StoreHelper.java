package by.issoft.store;

import by.issoft.store.populator.DBPopulator;
import by.issoft.store.populator.Populator;

public class StoreHelper {
    private Store store;


    public StoreHelper(Store store) {
        this.store = store;
    }

    public void fillStore(){
        //Меняем рандомстор на DBPopulator

        //Populator populator = new RandomStorePopulator(store);
        Populator populator = new DBPopulator(store);

        populator.fillStoreWithPopulator();
    }

}
