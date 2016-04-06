package Models.Map;

import Models.Entities.Entity;
import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Map {

    private HashMap<Point, ArrayList<Tile>> tiles;

    // Map will be passed the HashMap that is created by the gameloader after parsing the XML file.
    public Map(HashMap<Point, ArrayList<Tile>> tiles){
        this.tiles = tiles;
    }

    public void insertEntity(Point3D point, Entity entity){

        Point point2d = new Point((int)point.getX(), (int)point.getY());
        Tile tile = tiles.get(point2d).get((int)point.getZ());
//        tile.insertEntity(entity);
    }
}
