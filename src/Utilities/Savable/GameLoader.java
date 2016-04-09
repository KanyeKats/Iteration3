package Utilities.Savable;//package Utilities.Savable;
//

import Models.Entities.Entity;
import Models.Entities.Skills.Effects.Effect;
import Models.Items.Item;
import Models.Map.*;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXParseException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public class GameLoader {

    public static final Point3D DEFAULT_STARTING_POINT = new Point3D(0, 0, 0); // Just made sure this was a valid point in the default map. Should be done better probably.

//        public static void LoadAll() {
//        //loop through the savable objects and write them to a file
//
//
//        for (Tile tile : map.getTiles()) {
//            //load all entities, items, area effects
//            tile.load(readFromFile("Game0.txt"));
//        }
//
//        //load map
//        map.save(readFromFile("Map0.txt"));
//
//        //load key bindings
//        keyBinding.load(readFromFile("KeyBinding0.txt"));
//    }
//
//    private static void readFromFile(String fileName) {
//        // This will reference one line at a time
//        String line = null;
//        // This will be returned at the end of the function
//        ArrayList<String> data = new ArrayList<>();
//
//        try {
//            // FileReader reads text files in the default encoding.
//            FileReader fileReader = new FileReader(fileName);
//
//            // Always wrap FileReader in BufferedReader.
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            while((line = bufferedReader.readLine()) != null) {
//                data.add(line);
//            }
//
//            bufferedReader.close();
//        }
//        catch(FileNotFoundException ex) {
//            System.out.println("Unable to open file '" + fileName + "'");
//        }
//        catch(IOException ex) {
//            ex.printStackTrace();
//        }
//    }
}
