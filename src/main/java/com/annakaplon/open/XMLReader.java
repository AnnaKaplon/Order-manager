package com.annakaplon.open;

import com.annakaplon.exceptions.IncorrectDataException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.File;
import java.io.IOException;

import static com.annakaplon.Main.requestList;

/**
 * XMLReader provides methods necessary to turn data saved in XML files
 * into Request objects.
 */
public class XMLReader extends RequestsReader {

    /**
     * Reads (from given XML file) data about requests, converts it
     * into Request objects and adds to the global list.
     * @param fileName name of file to read
     */
    public void readFile(String fileName) {
        try{
            Document xmlDocument = getDocument(fileName);
            NodeList nList = xmlDocument.getElementsByTagName("request");
            for (int i = 0; i < nList.getLength(); i++){
                Node nNode = nList.item(i);
                Element eElement = (Element) nNode;
                createRequest(fileName, eElement);
            }
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Builds xml document.
     * @param fileName name of file
     * @return parsed xml document
     * @throws ParserConfigurationException
     * @throws IOException
     * @throws SAXException
     */
    private Document getDocument(String fileName) throws ParserConfigurationException, IOException, SAXException {
        File xmlFile = new File(fileName);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(xmlFile);
        doc.getDocumentElement().normalize();
        return doc;
    }

    /**
     * Reads data form given Element and convert it into Request object.
     * Ignores record and prints message if any filed is incorrect.
     * @param fileName name of file
     * @param el xml element
     */
    private void createRequest(String fileName, Element el){
        String clientId = el.getElementsByTagName("clientId")
                .item(0).getTextContent();
        String requestId = el.getElementsByTagName("requestId")
                .item(0).getTextContent();
        String name = el.getElementsByTagName("name")
                .item(0).getTextContent();
        String quantity = el.getElementsByTagName("quantity")
                .item(0).getTextContent();
        String price = el.getElementsByTagName("price")
                .item(0).getTextContent();

        try {
            requestList.add(new Request(clientId, requestId, name,
                    quantity, price));
        } catch (IncorrectDataException e) {
            System.out.println("Incorrect data in file " + fileName);
        }
    }
}