package by.issoft.store.ordering;

import by.issoft.domain.Product;
import lombok.SneakyThrows;

import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class SelectOrder implements Runnable{
    private int random;
    List<Product> products;
    final BlockingQueue<Product> purchasedGoods;

    public SelectOrder(int random, List<Product> products, BlockingQueue<Product> purchasedGoods) {
        this.random = random;
        this.products = products;
        this.purchasedGoods = purchasedGoods;
    }

    @SneakyThrows
    @Override
    public void run()  {
        TimeUnit.SECONDS.sleep(random);
        products.forEach(purchasedGoods::add);
        System.out.println("Ваш заказ:");
        for (Product purchasedGood : purchasedGoods) {
            System.out.println(purchasedGood);
        }
    }
}
