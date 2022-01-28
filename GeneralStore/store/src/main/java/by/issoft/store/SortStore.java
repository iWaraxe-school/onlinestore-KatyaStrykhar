package by.issoft.store;

import by.issoft.domain.Product;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.*;

public class SortStore {
    private Map<String, String> typeMap;
    private String sort;

    public SortStore() {
        this.typeMap = new HashMap<>();
        this.parsingXml();
        String sort = "price"; // сортировка по умолчанию по имени
    }

    public void setSort(String sort) {
        this.sort = sort;
    }

    public Map<String, String> getTypeSort() { return typeMap; }

    private void parsingXml (){
        String filePath = "GeneralStore/store/src/main/resources/configFile.xml";
        File file = new File(filePath);
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        Document doc = null;
        try {
            doc = dbf.newDocumentBuilder().parse(file);
        } catch (Exception e) { e.printStackTrace();}
        this.fillMap(doc);
    }

    private void fillMap(Document doc) {
        Node sortNode = doc.getFirstChild();
        NodeList sortChild = sortNode.getChildNodes();
        for (int i = 0; i < sortChild.getLength(); i++){
            String nodeName = sortChild.item(i).getNodeName();
            String textContent = sortChild.item(i).getTextContent();
            typeMap.put(nodeName, textContent);
        }
    }

    public List<Product> toSort(Store store){
        List<Product> products = store.getListOfAllProducts();
        List<Product> sortList = new ArrayList<>();
        String type = typeMap.get(sort);
        Comparator<Product> comp = ((Product a, Product b) -> a.getPrice() - b.getPrice());

        /*switch (sort){
            case "name":
                if (type.equals("asc")) comp = ((Product a, Product b) -> a.getName() - b.getName());
                else if (type.equals("desc")) comp = ((Product a, Product b) -> b.getName() - a.getName());
            break;
            case "price":
                if (type.equals("asc")) comp = ((Product a, Product b) -> a.getPrice() - b.getPrice());
                else if (type.equals("desc")) comp = ((Product a, Product b) -> b.getPrice() - a.getPrice());
            break;
            case "rate":
                if (type.equals("asc")) comp = ((Product a, Product b) -> a.getRate() - b.getRate());
                else if (type.equals("desc")) comp = ((Product a, Product b) -> b.getRate() - a.getRate());
            break;
        }*/
        products.stream()
                .sorted(comp)
                .forEach(sortList::add);
        return sortList;





    }


}
