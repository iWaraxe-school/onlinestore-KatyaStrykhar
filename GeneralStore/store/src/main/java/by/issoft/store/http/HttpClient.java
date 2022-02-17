package by.issoft.store.http;

import by.issoft.store.Store;
import io.restassured.RestAssured;

public class HttpClient {
    private Store store;
    public static final String USERNAME = "user";
    public static final String PASSWORD = "password";

    public HttpClient() {
        store = Store.getStore();
        store.cleanerPurcheses();              //запускаем клинер (интервал 2 минуты)

    }

    public void getProductsList() {
        RestAssured.given().auth().basic(USERNAME, PASSWORD).when().get("http://localhost:8080/products")
                .then().log().body();
    }

    public void addToCart() {
        RestAssured.given().auth().basic(USERNAME, PASSWORD).when().get("http://localhost:8080/cart")
                .then().log().body();
    }

}
