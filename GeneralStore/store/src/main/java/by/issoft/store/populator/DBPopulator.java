package by.issoft.store.populator;

import by.issoft.domain.Category;
import by.issoft.domain.Product;

import java.sql.*;
import java.util.LinkedHashMap;
import java.util.Map;

public class DBPopulator extends Populator{

    @Override
    public void fillStoreWithPopulator() {
        this.createSubTypesList(); //заполняем лист существующими категориями
        this.generateProducts(); //генерируем продукты и заполняем листы продуктов
        this.writeToDB();
    }

    private void printDB() {

        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:database")){
            try (Statement stmt = con.createStatement()){

                try (PreparedStatement query =
                             con.prepareStatement("SELECT * FROM CATEGORY")) {
                    ResultSet rs = query.executeQuery();
                    while (rs.next()) {
                        System.out.println(String.format("%s, %s", rs.getString("id"), rs.getString("name")));
                    }
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:database")){
            try (Statement stmt = con.createStatement()){

                try (PreparedStatement query =
                             con.prepareStatement("SELECT * FROM PRODUCTS")) {
                    ResultSet rs = query.executeQuery();
                    while (rs.next()) {
                        System.out.println(String.format("%s, %s, %s, %s, %s", rs.getString("id"), rs.getString("name"), rs.getString("rate"), rs.getString("price"), rs.getString("categoryName")));
                    }
                    rs.close();
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }


    }

    private void writeToDB() {
        Map<Product, Category> mapOfProducts = new LinkedHashMap<>();                   //subTypes - таблица Категории
        for(Category entry: subTypes){
            for (Product product: entry.getProductList()) {
                mapOfProducts.put(product, entry);          }}                  //mapOfProducts - мапа Продуктов, Категория


        try (Connection con = DriverManager.getConnection("jdbc:h2:mem:database")){
            try (Statement stmt = con.createStatement()){
                String createCategoryTable = "CREATE TABLE CATEGORY (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100));";
                stmt.execute(createCategoryTable);
                for(Category entry: subTypes) {
                    String dataQuery = "INSERT INTO CATEGORY (name) VALUES('" + entry.getName() + "');";
                    stmt.execute(dataQuery);
                }

                String createProductsTable = "CREATE TABLE PRODUCTS (id INT NOT NULL AUTO_INCREMENT PRIMARY KEY, name VARCHAR(100), rate DOUBLE, price INT, categoryName VARCHAR(100));";
                stmt.execute(createProductsTable);
                for(Map.Entry<Product, Category> entry: mapOfProducts.entrySet()) {
                    String name = entry.getKey().getName();
                    Double rate = entry.getKey().getRate();
                    int price = entry.getKey().getPrice();
                    String categoryName = entry.getValue().getName();
                    String dataQuery = "INSERT INTO PRODUCTS (name, rate, price, categoryName) VALUES('" + name + "', " + rate + ", " + price + ", '" + categoryName + "');";
                    stmt.execute(dataQuery);
                }

                //this.printDB();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
