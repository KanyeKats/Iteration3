package Utilities.Savable;

import Models.Map.*;
import Models.Map.AreaEffects.RiverAreaEffect;
import Utilities.Constants;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;
import java.util.HashMap;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public class GameLoader {

    // TODO: 4/9/16 this will be fixed once we have default maps
    public static final Point3D DEFAULT_STARTING_POINT = new Point3D(0, 0, 0); // Just made sure this was a valid point in the default map. Should be done better probably.

    //given an XML file load the map
    public static Map loadMap(String fileName) {
        Map map = new Map(new HashMap<>());
        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();

            //read the XML string
            File xmlFile = new File(fileName);
            Document doc = docBuilder.parse(xmlFile);

            //find MAP node
            NodeList mapList = doc.getElementsByTagName("map");
            Element mapElement = (Element) mapList.item(0);

            map.load(mapElement);

        } catch (SAXParseException e) {
            System.out.println("Error parsing");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }
        return map;
    }

    //HELPER METHOD: Just reads from a file and returns a string of the data
    private static String readFromFile(String fileName) {
        // This will reference one line at a time
        String line = null;
        // This will be returned at the end of the function
        String data = "";

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                data+=line;
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }

        return data;
    }
}
