//package Utilities.Savable;
//
//import java.nio.charset.Charset;
//import java.nio.file.Files;
//import java.nio.file.Path;
//import java.nio.file.Paths;
//import java.util.ArrayList;
//
///**
// * Created by johnkaufmann on 3/31/16.
// * TODO: finish and discuss with group
// */
//public class GameSaver {
//    private Map map;
//
//    public GameSaver(Map map) {
//        this.map = map;
//    }
//
//    public static void SaveAll() {
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
//    private static void writeToFile(ArrayList<String> data, String fileName) {
//        Path file = Paths.get(fileName);
//        try {
//            Files.write(file, data, Charset.forName("UTF-8"));
//        } catch (Exception e) {
//            System.out.println(e.getMessage());
//        }
//    }
//}
