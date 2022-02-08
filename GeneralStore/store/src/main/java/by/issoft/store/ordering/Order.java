package by.issoft.store.ordering;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.stream.Collectors;

public class Order {
    private List<Product> listOfProducts;
    final BlockingQueue<Product> purchasedGoods;
    private List<Product> products;


    public Order(List<Product> listOfProducts, BlockingQueue<Product> purchasedGoods) {
        this.listOfProducts = new ArrayList<>(listOfProducts);    //сделали копию листа продуктов
        this.purchasedGoods = purchasedGoods;                     //взяли ссылку на общую queue
        this.toStart();
    }

    private void toStart() {
        Random random = new Random();
        int randomInt = (int)(1 + random.nextInt(30));

        //выбираем рандомные 10 продуктов и кидаем их в лист продуктов
        products = listOfProducts.stream()
                .limit(10)                                                                      //или сколько угодно
                .map( i -> listOfProducts.get(random.nextInt(listOfProducts.size()-1)) )  //наверное есть более красивое решение
                .collect(Collectors.toList());


        Thread selectProducts = new Thread(new SelectOrder(randomInt, products, purchasedGoods));

        selectProducts.start();


    }
}
