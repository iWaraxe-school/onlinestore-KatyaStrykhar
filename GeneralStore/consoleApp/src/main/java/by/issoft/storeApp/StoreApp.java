package by.issoft.storeApp;

import by.issoft.store.Store;
import by.issoft.store.http.HttpClient;
import by.issoft.store.http.StoreHttpServer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class StoreApp {
    public static void main(String[] args) {

        Store store = Store.getStore();

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.println("\nUse http or DB?: ");
            String input = null;
            try {
                input = reader.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }

            switch (input) {
                case "DB":
                    store.useDB();
                    store.toStart();
                    BufferedReader readerCommand = new BufferedReader(new InputStreamReader(System.in));
                    Boolean flag = true;
                    while (flag) {
                        System.out.println("\nEnter command sort/top/exit/new order: ");
                        String command = null;
                        try {
                            command = readerCommand.readLine();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        switch (command) {
                            case "sort":
                                System.out.println("\nSorted products: ");
                                store.toSort();
                                break;
                            case "top":
                                System.out.println("\nTop products: ");
                                store.printTop();
                                break;
                            case "exit":
                                flag = false;
                                System.exit(0);
                                break;
                            case "new order":
                                store.addOrder();
                                break;
                            default:
                                System.out.println("invalid input");
                        }

                    }
                break;
                case "http":
                    new StoreHttpServer().startHttpServer();
                    HttpClient client = new HttpClient();
                    client.getProductsList();
                    client.addToCart();
                    break;

            }

    }
}



