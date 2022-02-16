package by.issoft.store;

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import by.issoft.store.ordering.CleanUp;
import by.issoft.store.ordering.Order;
import by.issoft.store.sorting.SortStore;

import java.util.*;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class Store {
    private static Store store;
    private List<Category> categoryList;
    private List<Product> listOfAllProducts;
    private SortStore sortMap;
    private StoreHelper helper;
    private final BlockingQueue<Product> purchasedGoods;
    private List<Order> orders;
    private boolean useDB;


    private Store() {
        categoryList = new ArrayList<>();
        listOfAllProducts = new ArrayList<>();
        sortMap = new SortStore();
        helper = new StoreHelper(this);
        purchasedGoods = new ArrayBlockingQueue<>(1024);
        useDB = false;
        orders = new ArrayList<>();
    }

    public static Store getStore() {
        if (store == null) {store = new Store();}
        return store;
    }

    public void toStart() {
        helper.fillStore(useDB);              //наполняем магазин фейкером или DB
        this.listAndPrintStore();             //создаем один лист всех продуктов и выводим его на печать
        this.cleanerPurcheses();              //запускаем клинер (интервал 2 минуты)

    }

    public List<Product> getListOfAllProducts() {
        return listOfAllProducts;
    }

    public BlockingQueue<Product> getPurchasedGoods() {
        return purchasedGoods;
    }

    public void printListOfProducts(){
        listOfAllProducts.stream()
                .forEach(Product::toPrint);
    }

    public void addCategory (Category category){
        categoryList.add(category);
    }

    public List<Category> getCategoryList() {
        return categoryList;
    }

    public void listAndPrintStore(){
        for (Category temp : categoryList){
            System.out.println("Категория: " + temp.getName());
            listOfAllProducts.addAll(temp.getProductList());
            for(Product prod: temp.getProductList()){
                prod.toPrint();
            }
        }
    }

    public void printTop() {
        Comparator<Product> comp = (a, b) -> b.getPrice() - a.getPrice();
        this.getListOfAllProducts().stream()
                .sorted(comp)
                .limit(5)
                .forEach(System.out::println);
    }

    public List<Product> toSort() {
        List<Product> sortedList = sortMap.toSort(listOfAllProducts);
        return sortedList; //возвращает отсортированный лист. Если дальше он нужен будет
    }

    public void cleanerPurcheses() {
        Thread cleanUp = new Thread(new CleanUp(purchasedGoods));
        cleanUp.start();

    }

    public void addOrder() {
        orders.add(new Order(listOfAllProducts, purchasedGoods));
    }

    public void addOrderHttp (Product product) {purchasedGoods.add(product);}

    /*
    public void setOrders(Order order) {
        orders = orders.add(order);
    }*/

    public void useDB() { useDB = true; }

    public void useRandomPopulator(){ useDB = false;}

    public Product getRandomProduct() {
        List<Product> list = new ArrayList<>();
        for (Category temp : categoryList){
            list.addAll(temp.getProductList());
        }
        Product any = list.stream().findAny().get();
        return any;
    }

    public String purToString() {
        return purchasedGoods.toString();
    }
}
