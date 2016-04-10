package Utilities.Savable;

import Models.Map.Map;

import java.io.FileNotFoundException;
import java.io.PrintWriter;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public class GameSaver {
    public static void saveMap(Map map) {
        writeToFile(map.save(), "./res/map/test.xml");
    }
    private static void writeToFile(String data, String fileName) {
        try(  PrintWriter out = new PrintWriter( fileName )  ){
            out.println( data );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
