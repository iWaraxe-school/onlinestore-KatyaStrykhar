package by.issoft.store.http;

import by.issoft.store.http.handler.CartHandler;
import by.issoft.store.http.handler.ProductsHandler;
import com.sun.net.httpserver.BasicAuthenticator;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.net.InetSocketAddress;

//здесь прописываем логику обработки запросов от HttpClient, вызываем HttpPopulator,
//либо можно просто вызвать RandomStorePopulator
//генерируем список продуктов и возвращаем HttpClient в виде листа продуктов



//также здесь находится метод обработки запроса "положить в корзину" через POST
//когда в StoreApp выбирают "new order", HttpClien создает запрос HttpServer, который создает объект класса Order


public class StoreHttpServer {

    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    public void startHttpServer() {
        try {
            HttpServer server = HttpServer.create(new InetSocketAddress(8080), 0);
            createContext(server, "/products", new ProductsHandler());
            createContext(server, "/cart", new CartHandler());
            server.setExecutor(null);
            server.start();
        } catch (Exception e){
            throw new RuntimeException(e.getMessage(), e);
        }
    }

    private void createContext(HttpServer server, String path, HttpHandler handler){
        server.createContext(path, handler).setAuthenticator(new BasicAuthenticator("test") {
            @Override
            public boolean checkCredentials(String username, String password) {
                return username.equals(USERNAME) && password.equals(PASSWORD);
            }
        });
    }
}

