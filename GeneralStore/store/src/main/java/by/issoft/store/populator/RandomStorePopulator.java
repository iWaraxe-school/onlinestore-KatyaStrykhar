package by.issoft.store.populator;

import com.github.javafaker.Faker;

public class RandomStorePopulator implements Populator {
    private Faker faker;

    public RandomStorePopulator() {faker = new Faker();}

    @Override
    public String createName(String categoryName) {
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

    @Override
    public Double createRate(){ return faker.number().randomDouble(1, 1, 5);}

    @Override
    public int createPrice(){ return (int) (Math.random()*100); }
}


