package by.issoft.store.http.handler;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;

public class CartHandler implements HttpHandler {

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        Store store = Store.getStore();
        Product product = store.getRandomProduct();
        store.addOrderHttp(product);
        String response = store.purToString();
        exchange.sendResponseHeaders(200, response.length());
        OutputStream outputStream = exchange.getResponseBody();
        outputStream.write(response.getBytes());
        outputStream.close();

    }
}
