package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.ArrayList;
import java.util.List;

public class DBPopulator extends Populator{

    public DBPopulator(Store store) {
        super(store);
    }

    @Override
    public void fillStoreWithPopulator() {
        this.createSubTypesList(); //заполняем лист существующими категориями
        this.generateProducts(); //генерируем продукты и заполняем листы продуктов


        List<List<Product>> ppppp = new ArrayList<>();
        for(Category entry: subTypes){
            List<Product> productsOfEntry = entry.getProductList();
            ppppp.add(productsOfEntry);
        }
        /* здесь логика заполнения БД продуктами и категориями */


    }
}
