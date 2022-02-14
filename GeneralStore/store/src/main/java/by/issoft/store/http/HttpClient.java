package by.issoft.store.http;

import by.issoft.domain.Product;
import by.issoft.store.Store;


import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class HttpClient {
    Store store;
    String login;
    String password;

    public HttpClient() {}

    public List<Product> getListOfProducts(Store store) {
        this.store = store;
        login = "Aladdin";
        password = "open sesame";
        String logoPass = login + ":" + password;

        //кодируем логоПароль


        //здесь открываем соединение и отправляем запрос GET на сервер (класс HttpServer) и в store записываем возвращаемый лист продуктов

    }


    public void addToCart(){

        //отправляем запрос на сервер "положить в корзину", там формируется заказ new Order()
        //вся логика метода Store.addOrder() переносится в HttpServer

    }





}
