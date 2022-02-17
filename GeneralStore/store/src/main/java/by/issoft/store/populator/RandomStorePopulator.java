package by.issoft.store.populator;

public class RandomStorePopulator extends Populator {

    @Override
    public void fillStoreWithPopulator() {
        this.createSubTypesList(); //заполняем лист существующими категориями
        this.generateProducts(); //генерируем продукты и заполняем листы продуктов
    }

}


