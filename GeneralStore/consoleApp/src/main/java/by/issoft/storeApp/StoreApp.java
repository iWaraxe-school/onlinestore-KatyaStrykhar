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

        testSorting(store, sorting);

    }

    private static void testSorting(Store store, SortStore sorting) {
        List<Product> sortByName = sorting.toSort(store, "name");

        System.out.println("");
        System.out.println("Сортировка по имени");
        for(var prod: sortByName){
            System.out.println(prod);
        }

        List<Product> sortByRate = sorting.toSort(store, "rate");

        System.out.println("");
        System.out.println("Сортировка по ставке");
        for(var prod: sortByRate){
            System.out.println(prod);
        }

        List<Product> sortByPrice = sorting.toSort(store, "price");

        System.out.println("");
        System.out.println("Сортировка по цене");
        for(var prod: sortByPrice){
            System.out.println(prod);
        }
    }
}


