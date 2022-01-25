package by.issoft.store;

import com.github.javafaker.Faker;

public class RandomStorePopulator {
    private Faker faker;


    public RandomStorePopulator() {faker = new Faker();}

    public String toCreateName(String categoryName) {
        String ret = null;
        switch (categoryName){
                case "milk":
                    ret = faker.food().ingredient();
                    break;
                case "phone":
                    ret = faker.company().name();
                    break;
                case "bike":
                    ret = faker.company().name();
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + categoryName);
            }
            return ret;
    }

    public Double toCreateRate(){
        return faker.number().randomDouble(1, 1, 5);
    }
    public int toCreatePrice(){
        return (int) (Math.random()*100);
    }
}


