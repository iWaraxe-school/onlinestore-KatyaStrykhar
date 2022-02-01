package by.issoft.storeApp;

import by.issoft.domain.Product;
import by.issoft.store.SortStore;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.List;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);

        helper.fillStore();
        store.fillAndPrintStore();//создаем одил лист всех продуктов и выводим его на печать

        SortStore sorting = new SortStore();//создаем SortStore(при инициализации парсим наш xml, создаем мапу как сортировать по какому полю

        //testSorting(store, sorting);

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String command = null;
        try {
            command = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
        }

        switch (command){
            case "sort":
                List<Product> sortByName = sorting.toSort(store, "name");
                System.out.println("\nСортировка по имени");
                sortByName.stream().forEach(System.out::println);
                break;
            case "top":
                printTop(store, 5);
                break;
            case "exit":
                System. exit(0);
                break;
            default: System.out.println("invalid input");
        }

    }

    public static void printTop(Store store, int i){
        Comparator<Product> comp = (a, b) -> b.getPrice() - a.getPrice();
        System.out.println("Top price: ");
        List<Product> products = store.getListOfAllProducts();
        products.stream()
                .sorted(comp)
                .limit(i)
                .forEach(System.out::println);
    }

    private static void testSorting(Store store, SortStore sorting) {
        List<Product> sortByName = sorting.toSort(store, "name");
        System.out.println("\nСортировка по имени");
        sortByName.stream().forEach(System.out::println);

        List<Product> sortByRate = sorting.toSort(store, "rate");
        System.out.println("\nСортировка по ставке");
        sortByRate.stream().forEach(System.out::println);

        List<Product> sortByPrice = sorting.toSort(store, "price");
        System.out.println("\nСортировка по цене");
        sortByPrice.stream().forEach(System.out::println);
    }
}


