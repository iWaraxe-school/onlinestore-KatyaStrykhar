package by.issoft.store.ordering;

import by.issoft.domain.Product;
import by.issoft.store.Store;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class Order {
    private List<Product> listOfProducts;
    final BlockingQueue<Product> purchasedGoods;


    public Order(Store store) {
        listOfProducts = new ArrayList<>(store.getListOfAllProducts());
        purchasedGoods = new ArrayBlockingQueue<>(1024);
        this.toStart();
    }

    private void toStart() {
        Random random = new Random();
        int randomInt = (int)(1 + random.nextInt(30));

        //выбираем рандомные 10 продуктов и кидаем его в лист продуктов
        List<Product> products = listOfProducts.stream()
                .limit(10)                                                                      //или сколько угодно
                .map( i -> listOfProducts.get(random.nextInt(listOfProducts.size()-1)) )  //наверное есть более красивое решение
                .collect(Collectors.toList());


        Thread selectProducts = new Thread(new SelectOrder(randomInt, products, purchasedGoods));
        Thread cleanUp = new Thread(new CleanUp(purchasedGoods));

        selectProducts.start();
        cleanUp.start();

    }
}
