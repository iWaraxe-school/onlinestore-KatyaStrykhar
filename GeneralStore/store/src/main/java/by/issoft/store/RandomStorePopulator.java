package by.issoft.store;

// служебный класс, с помощью Faker создает новые экземпляры Product разных категорий

/*
Faker faker = new Faker();

String name = faker.name().fullName(); // Miss Samanta Schmidt
String firstName = faker.name().firstName(); // Emory
String lastName = faker.name().lastName(); // Barton

String streetAddress = faker.address().streetAddress(); // 60018 Sawayn Brooks Suite 449
*/

import by.issoft.domain.Category;
import by.issoft.domain.Product;
import com.github.javafaker.Faker;

import java.nio.DoubleBuffer;

public class RandomStorePopulator {
    private Faker faker;


    public RandomStorePopulator() {
        faker = new Faker();

    }

    public void toCreate(Category category) {
        String cat = category.getName();
        int i = 5 + ((int) (Math.random() * 6));

        for (int j = 0; j<i; j++){
            double rate;
            int price;
            String name;
            switch (cat){
                case "milk":
                    name = faker.food().ingredient();
                    rate = faker.number().randomDouble(1, 1, 5);
                    price = (int) Math.random()*51;
                    break;
                case "phone":
                    name = faker.company().name();
                    rate = faker.number().randomDouble(1, 1, 3);
                    price = (int) Math.random()*951;
                    break;
                case "bike":
                    name = faker.company().name();
                    rate = faker.number().randomDouble(1, 1, 2);
                    price = (int) Math.random()*501;
                    break;
                default System.out.println("Я не знаю такой категории"); // не самая серьезная обработка ошибок, конечно
            }
            category.putProductToList(new Product(name, rate, price));
            }
    }
}
