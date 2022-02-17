package by.issoft.store.http.handler;

import by.issoft.domain.Product;
import by.issoft.store.populator.HttpPopulator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.net.httpserver.Headers;
import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Objects;

public class ProductsHandler implements HttpHandler {


    @Override
    public void handle(HttpExchange exchange) throws IOException {
        List<Product> products = new HttpPopulator().getList();
        ObjectMapper mapper = new ObjectMapper();
        OutputStream outputStream = null;

        try{
            byte[] jsoninBytes = mapper.writeValueAsBytes(products);
            Headers headers = exchange.getResponseHeaders();
            headers.add("Content-Type", "application/json");
            exchange.sendResponseHeaders(200, jsoninBytes.length);
            outputStream = exchange.getResponseBody();
            outputStream.write(jsoninBytes);
        } finally {
            if(Objects.nonNull(outputStream)){
                outputStream.close();
            }
        }

    }

}
