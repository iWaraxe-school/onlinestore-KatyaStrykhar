package by.issoft.store.sorting;

import by.issoft.domain.Product;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

//создаем SortStore(при инициализации парсим наш xml, создаем мапу как сортировать по какому полю

public class SortStore {
    private XmlReader reader;

    public SortStore() {
        reader = new XmlReader();
    }

    public List<Product> toSort(List<Product> products) {
        List<Comparator<Product>> compList = this.createListOfComparators();
        Comparator<Product> comp = this.createCompForStream(compList);
        List<Product> sortedList = new ArrayList<>();
        products.stream()
                .sorted(comp)
                .forEach(sortedList::add);

        sortedList.forEach(System.out::println);
        return sortedList;
    }

    private List<Comparator<Product>> createListOfComparators() {
        Map<String, String> typeSort = reader.getMap();
        List<Comparator<Product>> compList = new ArrayList<>();

        for(Map.Entry<String, String> type: typeSort.entrySet()) {
            String key = type.getKey();
            String value = type.getValue();
            Comparator<Product> comp = (a, b) -> b.getPrice() - a.getPrice();
            switch (key) {
                case "name":
                    System.out.println(key + " = " + value);
                    if (value.equals("asc")) comp = (a, b) -> a.getName().compareTo(b.getName());
                    else if (value.equals("desc")) comp = (a, b) -> b.getName().compareTo(a.getName());
                    compList.add(comp);
                    break;
                case "price":
                    System.out.println(key + " = " + value);
                    if (value.equals("asc")) comp = ((a, b) -> a.getPrice() - b.getPrice());
                    else if (value.equals("desc")) comp = ((a, b) -> b.getPrice() - a.getPrice());
                    compList.add(comp);
                    break;
                case "rate":
                    System.out.println(key + " = " + value);
                    if (value.equals("asc")) comp = (Comparator.comparingDouble(Product::getRate));
                    else if (value.equals("desc")) comp = ((a, b) -> Double.compare(b.getRate(), a.getRate()));
                    compList.add(comp);
                    break;
            }
        }
        return compList;
    }

    private Comparator<Product> createCompForStream(List<Comparator<Product>> compList) {

        Comparator<Product> lastComp = compList.get(compList.size()-1);

        for (int i = compList.size(); i >1; i--){
            Comparator<Product> preLastComp = compList.get(i-2);
            Comparator<Product> compComp = preLastComp.thenComparing(lastComp);
            lastComp = compComp;
        }
        return lastComp;
    }
}

