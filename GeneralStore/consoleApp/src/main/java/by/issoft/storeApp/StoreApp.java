package by.issoft.storeApp;

import by.issoft.store.sorting.SortStore;
import by.issoft.store.Store;
import by.issoft.store.StoreHelper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {
    public static void main(String[] args) {

        Store store = new Store();
        StoreHelper helper = new StoreHelper(store);

        helper.fillStore();
        store.fillAndPrintStore();//создаем одил лист всех продуктов и выводим его на печать

        SortStore sorting = new SortStore();//создаем SortStore(при инициализации парсим наш xml, создаем мапу как сортировать по какому полю

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        Boolean flag = true;
        while (flag) {
            System.out.println("Enter command sort/top/exit: ");
            String command = null;
            try {
                command = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (command) {
                case "sort":
                    System.out.println("\nSorted products: ");
                /*List<Product> sortByName = sorting.toSort(store, "name");
                sortByName.stream().forEach(System.out::println);
                 */
                    break;
                case "top":
                    System.out.println("\nTop products: ");
                    store.printTop();
                    break;
                case "exit":
                    flag = false;
                    System.exit(0);
                    break;
                default:
                    System.out.println("invalid input");
            }
        }
    }
}



