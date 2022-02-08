package by.issoft.store.ordering;

import by.issoft.domain.Product;
import lombok.SneakyThrows;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class CleanUp implements Runnable{
    final BlockingQueue<Product> purchasedGoods;
    private boolean isTrue = true;

    public CleanUp(BlockingQueue<Product> purchasedGoods) {
        this.purchasedGoods = purchasedGoods;
    }

    @SneakyThrows
    @Override
    public void run() {
        while (isTrue) {
            if (!(purchasedGoods.size()==0)) {
                TimeUnit.MINUTES.sleep(2);
                purchasedGoods.clear();
                System.out.println("\nВаш заказ оформлен");
                isTrue = false; //это можно закомментить, если надо, чтобы он каждые 2 минуты работал без остановки
            }
        }

    }
}
