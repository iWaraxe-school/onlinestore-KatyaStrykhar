package by.issoft.store.populator;

import by.issoft.domain.Product;

import java.util.List;

public class HttpPopulator extends Populator{

    @Override
    public void fillStoreWithPopulator() {
        this.createSubTypesList(); //заполняем лист существующими категориями
        this.generateProducts(); //генерируем продукты и заполняем листы продуктов
    }

    public List<Product> getList(){
        fillStoreWithPopulator();
        return prodList;
    }
}
