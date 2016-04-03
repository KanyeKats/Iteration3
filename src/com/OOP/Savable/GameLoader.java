package com.OOP.Savable;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 3/31/16.
 * TODO: finish and discuss with group
 */
public class GameLoader {

    public static void LoadAll() {
        //loop through the savable objects and write them to a file


        for (Tile tile : map.getTiles()) {
            //load all entities, items, area effects
            tile.load(readFromFile("Game0.txt"));
        }

        //load map
        map.save(readFromFile("Map0.txt"));

        //load key bindings
        keyBinding.load(readFromFile("KeyBinding0.txt"));
    }

    private static void readFromFile(String fileName) {
        // This will reference one line at a time
        String line = null;
        // This will be returned at the end of the function
        ArrayList<String> data = new ArrayList<>();

        try {
            // FileReader reads text files in the default encoding.
            FileReader fileReader = new FileReader(fileName);

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            while((line = bufferedReader.readLine()) != null) {
                data.add(line);
            }

            bufferedReader.close();
        }
        catch(FileNotFoundException ex) {
            System.out.println("Unable to open file '" + fileName + "'");
        }
        catch(IOException ex) {
            ex.printStackTrace();
        }
    }
}
