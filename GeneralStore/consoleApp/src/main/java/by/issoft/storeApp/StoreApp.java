package by.issoft.storeApp;

import by.issoft.domain.Product;
import by.issoft.store.SortStore;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

import java.util.List;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);

        helper.fillStore();
        store.fillAndPrintStore();//создаем одил лист всех продуктов и выводим его на печать

        SortStore sorting = new SortStore();//создаем SortStore(при инициализации парсим наш xml, создаем мапу как сортировать по какому полю
        // sorting.setSort("name") задаем способ сортировки. По умолчанию стоит price asc. Не обязательно устанавливать сразу.
        List<Product> sortList = sorting.toSort(store);

        System.out.println("");
        for(var prod: sortList){
            System.out.println(prod);
        }


    }
}


