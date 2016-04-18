package Utilities.Savable;

import Models.Map.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.StringWriter;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public class GameSaver {
    public static void saveMap(Map map) {
        try {
            DocumentBuilderFactory dFact = DocumentBuilderFactory.newInstance();
            DocumentBuilder build = dFact.newDocumentBuilder();
            Document doc = build.newDocument();

            Element root = doc.createElement("game");
            doc.appendChild(root);

            map.save(doc, root);

            TransformerFactory tFact = TransformerFactory.newInstance();
            Transformer trans = tFact.newTransformer();
            trans.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            DOMSource source = new DOMSource(doc);
            trans.transform(source, result);

            StringBuffer sb = writer.getBuffer();
            String finalString = sb.toString();

            writeToFile(finalString, "./res/map/saved.xml");

        } catch (TransformerException ex) {
            System.out.println("Error outputting document");
        } catch (ParserConfigurationException ex) {
            System.out.println("Error building document");
        }
    }
    private static void writeToFile(String data, String fileName) {
        try(  PrintWriter out = new PrintWriter( fileName )  ){
            out.println( data );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
