package by.issoft.store.populator;

import by.issoft.store.Store;

public class RandomStorePopulator extends Populator {

    public RandomStorePopulator(Store store) {
        super(store);
    }

    @Override
    public void fillStoreWithPopulator() {
        this.createSubTypesList(); //заполняем лист существующими категориями
        this.generateProducts(); //генерируем продукты и заполняем листы продуктов
    }

}


