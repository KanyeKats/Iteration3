package Utilities.Savable;

import Models.Map.Map;
import Models.Map.Tile;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 3/31/16.
 * TODO: finish and discuss with group
 */
public class GameSaver {
    public static void saveMap(Map map) {
        writeToFile(map.save(), "./res/map/test.xml");
    }
//    private Map map;
//
//    public GameSaver(Map map) {
//        this.map = map;
//    }
//
//    public void SaveAll() {
//        //loop through the savable objects and write them to a file
//        for (Tile tile : map.getTiles()) {
//            //save all entities, save all items, save all area effects
//            writeToFile(tile.save(), "Game0.txt");
//        }
//
//        //save map
//        writeToFile(map.save(), "Map0.txt");
//
//        //save key bindings
//        writeToFile(keyBinding.save(), "KeyBinding0.txt");
//    }
//
    private static void writeToFile(String data, String fileName) {
        try(  PrintWriter out = new PrintWriter( fileName )  ){
            out.println( data );
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
}
