package Utilities.Savable;

import Models.Map.*;
import javafx.geometry.Point3D;
import java.io.*;
import java.util.HashMap;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public class GameLoader {

    public static final Point3D DEFAULT_STARTING_POINT = new Point3D(0, 0, 0); // Just made sure this was a valid point in the default map. Should be done better probably.

    public static Map loadMap(String fileName) {
        Map map = new Map(new HashMap<>());
        map.load(readFromFile(fileName));
        return map;
    }

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
