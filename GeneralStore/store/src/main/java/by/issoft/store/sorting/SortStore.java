package by.issoft.store.sorting;

import by.issoft.domain.Product;
import by.issoft.store.Store;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class SortStore {
    private Map<String, String> typeMap;

    public SortStore() {
        this.typeMap = new HashMap<>();
        this.parsingXml();
    }

    private void parsingXml() {
        String filePath = "GeneralStore/store/src/main/resources/configFile.xml";
        File file = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.fillMap(doc);
    }

    private void fillMap(Document doc) {
        Node sortNode = doc.getFirstChild();
        NodeList sortChild = sortNode.getChildNodes();
        for (int i = 0; i < sortChild.getLength(); i++) {
            String nodeName = sortChild.item(i).getNodeName();
            String textContent = sortChild.item(i).getTextContent();
            typeMap.put(nodeName, textContent);
        }
    }

    public List<Product> toSort(Store store, String how) {
        List<Product> products = store.getListOfAllProducts();
        List<Product> sortList = new ArrayList<>();
        String sorter = how;
        String type = typeMap.get(sorter);

        Comparator<Product> comp = (a, b) -> b.getPrice() - a.getPrice();

        switch (sorter) {
            case "name":
                if (type.equals("asc")) comp = (a, b) -> a.getName().compareTo(b.getName());
                else if (type.equals("desc")) comp = (a, b) -> b.getName().compareTo(a.getName());
                break;
            case "price":
                if (type.equals("asc")) comp = ((a, b) -> a.getPrice() - b.getPrice());
                else if (type.equals("desc")) comp = ((a, b) -> b.getPrice() - a.getPrice());
                break;
            case "rate":
                if (type.equals("asc")) comp = (Comparator.comparingDouble(Product::getRate));
                else if (type.equals("desc")) comp = ((a, b) -> Double.compare(b.getRate(), a.getRate()));
                break;
        }

        products.stream()
                .sorted(comp)
                .forEach(sortList::add);
        return sortList;


    }

}

