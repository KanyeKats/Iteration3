package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.Effects.Effect;
import Models.Items.Item;
import Models.Map.MapUtilities.MapDrawingVisitor;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Map extends Observable {

    private HashMap<Point3D, Tile> tiles;
    // TODO: Not really a todo but make sure you notify observers when you change something that will affect the visual representation.

    // Map will be passed the HashMap that is created by the gameloader after parsing the XML file.
    public Map(HashMap<Point3D, Tile> tiles){
        this.tiles = tiles;
    }

    public void insertEntity(Entity entity, Point3D point){

        // Get the tile at the given point.
        Tile tile = tiles.get(point);

        // Insert the entity onto the tile.
        tile.insertEntity(entity);

        // Update the entity's location
        entity.setLocation(point);
    }

    // This should only be used when an Entity is dead.
    public void removeEntity(Point3D point){

        // Get the tile at the given point.
        Tile tile = tiles.get(point);

        // Remove the entity at the tile.
        tile.removeEntity();
    }

    public void moveEntity(Entity entity, Point3D destination){

        // TODO: Implement movement. The following things will need to be done.
        // TODO: 1) Check if there is an obstacle or interactive item that will prevent movement.
        // TODO:    This can be done with tile.getItem().preventsMovement(); This violates LOD though.
        // TODO: 2) Check fi there is an entity on the same tile already.
        // TODO: 3) Check the terrain type (if its water, and also eleveation differences
        // TODO:    If the desired tile is too high, prevent movement, if it is lower, fall to the earth and deal damage.

        // Update the destination point
        // This method will return the appropirate destination tile by checking all movement related factors.
        // See its comments for more info
        destination = checkDestinationTile(destination, entity);

        Tile destinationTile = tiles.get(destination);

        // NOTE: The activation of area effects and items is the responsibility of the tile once it moves onto it.



        // Get the source tile.
        Point3D source = entity.getLocation();
        Tile sourceTile = tiles.get(source);

        // Check if the tiles are in bounds of the map.
        if(sourceTile==null || destinationTile==null){
            return;
        }


        // Remove the entity from the source and add it to the destination.
        sourceTile.removeEntity();
        destinationTile.insertEntity(entity);

        // Update the entity's location
        entity.setLocation(destination);

        // Notify observers taht the map changes
        setChanged();
        notifyObservers();
    }

    public void moveEntity(Entity entity, Direction direction){

        Point3D destination = direction.getPointAdjacentTo(entity.getLocation());
        moveEntity(entity, destination);
    }


    public void insertItem(Item item, Point3D point){

        // Get the tile.
        Tile tile = tiles.get(point);

        // Insert the item onto that tile.
        tile.insertItem(item);
    }

    public void removeItem(Item item, Point3D point){

        // Get the tile.
        Tile tile = tiles.get(point);

        // Insert the item onto that tile.
        tile.removeItem(item);
    }

    public void insertAreaEffect(AreaEffect areaEffect, Point3D point){

        // Get the tile.
        Tile tile = tiles.get(point);

        // Insert the areaEffect onto that tile.
        tile.insertAreaEffect(areaEffect);
    }

    public void removeAreaEffect(Point3D point){

        // Get the tile.
        Tile tile = tiles.get(point);

        // Remove the areaeffect on that tile.
        tile.removeAreaEffect();
    }

    public void insertEffect(Effect effect, Point3D point){

        // Get the tile.
        Tile tile = tiles.get(point);

        // Insert the areaEffect onto that tile.
        tile.insertEffect(effect);
    }

    public void removeEffect(Point3D point){

        // Get the tile.
        Tile tile = tiles.get(point);

        // Remove the areaeffect on that tile.
        tile.removeEffect();
    }

    public void acceptDrawingVisitor(MapDrawingVisitor visitor){
        visitor.accept(tiles);
    }

    // Checks the destination tile for movement hindrance and items/AoEs/Entities
    private Point3D checkDestinationTile(Point3D originalDestinationPoint, Entity entity) {

        // Get the original destination tile
        Tile originalDestinationTile = tiles.get(originalDestinationPoint);

        // TODO: Check for terrain type
        Terrain terrain = originalDestinationTile.getTerrain();

        // Get the entity's current point location before any movement
        Point3D entityCurrentLocation = entity.getLocation();
        
        // Compare height of dest. point, with entity's current height
        double range = 1.0;
        double entityCurrentZ = entityCurrentLocation.getZ();

        // Get the max column height at this x,y position.
        int destinationPointX = (int)originalDestinationPoint.getX();
        int destinationPointY = (int)originalDestinationPoint.getY();

        double destinationMaxZHeight = getMaxColumnHeightAtPoint(destinationPointX, destinationPointY);

        System.out.println("THE MAX COLUMN HEIGHT AT THE TILE WERE ATTEMPTING TO MOVE AT IS ");
        System.out.println(destinationMaxZHeight);

        // If on the same height level, allow normal movement
        if (entityCurrentZ == destinationMaxZHeight) {
            return originalDestinationPoint;
        }
        // The column is too tall, block movement
        else if (destinationMaxZHeight > entityCurrentZ + range) {
            return entityCurrentLocation;
        }
        // If destination max Z is greater than entity's current z by one. we can move up one.
        // OR we are at a cliff.... need to drop down!!
        else if (destinationMaxZHeight - entityCurrentZ == range || entityCurrentZ - destinationMaxZHeight > 0){
            Point3D updatedDestinationPoint = new Point3D(destinationPointX, destinationPointY, destinationMaxZHeight);
            return updatedDestinationPoint;
        }
        // Check for hindrance of movement
        // If hindered, Don't allow the entity to move.
        // Return its current location is the "new" destination
        else if (originalDestinationTile.preventsMovement(entity)) {
            return entityCurrentLocation;
        }
        // Otherwise.. allow movement!
        else {
            return originalDestinationPoint;
        }
    }

    public double getMaxColumnHeightAtPoint(int x, int y) {
        // Max column height is 10.
        // TODO: define this 10 constant somewhere

        double maxZHeight = 0.0;

        Point3D pointToCheck;
        Tile tileToCheck;
        for (int z = 0; z < 10; z++) {
            pointToCheck = new Point3D(x, y, z);
            tileToCheck = tiles.get(pointToCheck);

            // TODO: make this a while loop using this condition lol....
            if (tileToCheck.getTerrain() == Terrain.SKY) {
                break;
            }

            maxZHeight = z;
        }
        return maxZHeight;
    }

}
