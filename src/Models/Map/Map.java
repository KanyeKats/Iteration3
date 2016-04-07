package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.Effects.Effect;
import Models.Items.Item;
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

    public void insertEntity(Entity entity, Point3D point){

        // Get the tile at the given point.
        Tile tile = getTileFromPoint(point);

        // Insert the entity onto the tile.
        tile.insertEntity(entity);

        // Update the entity's location
        entity.setLocation(point);
    }

    // This should only be used when an Entity is dead.
    public void removeEntity(Point3D point){

        // Get the tile at the given point.
        Tile tile = getTileFromPoint(point);

        // Remove the entity at the tile.
        tile.removeEntity();
    }

    public void moveEntity(Entity entity, Point3D destination){

        // Get the destination tile.
        Tile destinationTile = getTileFromPoint(destination);

        // Get the source tile.
        Point3D source = entity.getLocation();
        Tile sourceTile = getTileFromPoint(source);

        // Check movement conditions
        if(!entityCanMove(sourceTile, destinationTile)){
            return;
        }

        // Remove the entity from the source and add it to the destination.
        sourceTile.removeEntity();
        destinationTile.insertEntity(entity);

        // Update the entity's location
        entity.setLocation(destination);
    }

    public void moveEntity(Entity entity, Direction direction){

        Point3D destination = direction.getPointAdjacentTo(entity.getLocation());
        moveEntity(entity, destination);
    }

    private boolean entityCanMove(Tile source, Tile destination){

        // Check if the tile is in range of the map.
        if(source==null || destination==null){
            return false;
        }

        // TODO: Figure out how to implement movement. Its kinda weird with entities falling off cliffs/ some flying/ some walking on water.
    }

    public void insertItem(Item item, Point3D point){

        // Get the tile.
        Tile tile = getTileFromPoint(point);

        // Insert the item onto that tile.
        tile.insertItem(item);
    }

    public void removeItem(Item item, Point3D point){

        // Get the tile.
        Tile tile = getTileFromPoint(point);

        // Insert the item onto that tile.
        tile.removeItem(item);
    }

    public void insertAreaEffect(AreaEffect areaEffect, Point3D point){

        // Get the tile.
        Tile tile = getTileFromPoint(point);

        // Insert the areaEffect onto that tile.
        tile.insertAreaEffect(areaEffect);
    }

    public void removeAreaEffect(Point3D point){

        // Get the tile.
        Tile tile = getTileFromPoint(point);

        // Remove the areaeffect on that tile.
        tile.removeAreaEffect();
    }

    public void insertEffect(Effect effect, Point3D point){

        // Get the tile.
        Tile tile = getTileFromPoint(point);

        // Insert the areaEffect onto that tile.
        tile.insertEffect(effect);
    }

    public void removeEffect(Point3D point){

        // Get the tile.
        Tile tile = getTileFromPoint(point);

        // Remove the areaeffect on that tile.
        tile.removeEffect();
    }

    public Image getTileImage(){
        // TODO: Implement this.
        return null;
    }

    private Tile getTileFromPoint(Point3D point){
        Point point2d = new Point((int)point.getX(), (int)point.getY());
        return  tiles.get(point2d).get((int)point.getZ());
    }
}
