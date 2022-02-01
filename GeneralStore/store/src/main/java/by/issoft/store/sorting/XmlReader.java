package by.issoft.store.sorting;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.LinkedHashMap;
import java.util.Map;

public class XmlReader {
    private Map<String, String> map;

    public XmlReader() {
        map = new LinkedHashMap<>();
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
            map.put(nodeName, textContent);
        }
    }

    public Map<String, String> getMap() {
        return map;
    }
}
