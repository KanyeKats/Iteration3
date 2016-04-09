package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.Effects.Effect;
import Models.Entities.Stats.Stat;
import Models.Items.Item;
import Models.Map.MapUtilities.MapDrawingVisitor;
import Utilities.Constants;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Observable;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Map extends Observable {

    //// CLASS DECLARATIONS ////

    private HashMap<Point3D, Tile> tiles;
    // TODO: Not really a todo but make sure you notify observers when you change something that will affect the visual representation.

    // Map will be passed the HashMap that is created by the gameloader after parsing the XML file.
    public Map(HashMap<Point3D, Tile> tiles){
        this.tiles = tiles;
    }


    //// MOVEMENT FUNCTIONS ////

    // The following things are being taken into consideration for movement:
    // If the height difference of desired tile is too tall, prevent movement,
    // If within one height difference allow movement, if there is a cliff, drop entity
    // to the bottom of the cliff. TODO: will need to check if we dropped off a cliff + deal damage.
    // Next, we check for an item (obstacle/interactive) or entity which will block movement.
    // Finally, we check the terrain type of the updated destination
    public void moveEntity(Entity entity, Point3D destination){
        // Update the destination point
        // This method will return the appropirate destination tile by checking all movement related factors.
        // See its comments for more info
        destination = updateDestinationPoint(destination, entity);

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

    //// MAP MODIFIERS ////

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


    //// MOVEMENT CHECKERS ////

    // Checks the destination tile for movement hindrance and height differences
    // Returns an updated destination point based on the above factos
    private Point3D updateDestinationPoint(Point3D originalDestinationPoint, Entity entity) {

        // Get the entity's current point location before any movement
        Point3D entityCurrentLocation = entity.getLocation();

        // First, update the destination point based off of height differences
        // i.e: Too tall, fall off cliff, climb up 1 step, or move to a tile at the same height.
        Point3D updatedDestinationPointBasedOffHeight = updateDestinationPointBasedOnHeight(originalDestinationPoint, entityCurrentLocation);

        // Get the updated destination tile
        Tile updatedDestinationTileBasedOffHeight = tiles.get(updatedDestinationPointBasedOffHeight);

        // Compare terrain restrictions
        Terrain terrain = updatedDestinationTileBasedOffHeight.getTerrain();
        ArrayList<Terrain> passableTerrains = entity.getPassableTerrains();
        boolean passable  = terrain.inList(passableTerrains);

        // Now, check for hindrance of movement by an entity / item (obstacle/interactive)
        // along with wither or not the terrain on the new updated destination tile is passable.
        // If hindered, Don't allow the entity to move.
        // Return its current location as the "new" destination
        if (updatedDestinationTileBasedOffHeight.preventsMovement(entity) || !passable) {
            return entityCurrentLocation;
        }
        // Otherwise.. allow to pass!
        else {
            return updatedDestinationPointBasedOffHeight;
        }
    }

    private Point3D updateDestinationPointBasedOnHeight(Point3D originalDestinationPoint, Point3D entityCurrentLocation) {
        // Compare height of dest. point, with entity's current height
        int tolerance = 1;
        int entityCurrentZ = (int)entityCurrentLocation.getZ();

        // Get the max column height at this x,y position.
        int destinationPointX = (int)originalDestinationPoint.getX();
        int destinationPointY = (int)originalDestinationPoint.getY();

        double destinationMaxZHeight = getMaxColumnHeightAtPoint(destinationPointX, destinationPointY);

        System.out.println("THE MAX COLUMN HEIGHT AT THE TILE WERE ATTEMPTING TO MOVE AT IS ");
        System.out.println(destinationMaxZHeight);

        // The column is too tall, block movement
        if (destinationMaxZHeight > entityCurrentZ + tolerance) {
            return entityCurrentLocation;
        }
        // If destination max Z is greater than entity's current z by one. we can move up one.
        // OR, if destination max Z is below entity's current Z: we are at a cliff.... need to drop down!!
        else if (destinationMaxZHeight - entityCurrentZ == tolerance || entityCurrentZ - destinationMaxZHeight > 0){
            return new Point3D(destinationPointX, destinationPointY, destinationMaxZHeight);
        }
        // Else, If on the same height level, allow normal movement
        // ^ entityCurrentZ == destinationMaxZHeight ^
        else  {
            return originalDestinationPoint;
        }
    }

    //// HELPERS ////

    public int getMaxColumnHeightAtPoint(int x, int y) {
        // Start at the lowest z point
        int z = 0;

        // Get tile and point
        Point3D pointToCheck = new Point3D(x, y, z);
        Tile tileToCheck = tiles.get(pointToCheck);

        // While we aren't at sky keep incrementing z and checking
        while (z < Constants.COLUMN_HEIGHT && tileToCheck.getTerrain() != Terrain.SKY) {
            z++;
            pointToCheck = new Point3D(x, y, z);
            tileToCheck = tiles.get(pointToCheck);
        }
        return z - 1;
    }

    public int getColumnHeightDifferenceBetween2DPoints(Point pointA, Point pointB) {
        int pointAMaxHeight = getMaxColumnHeightAtPoint((int)pointA.getX(), (int)pointA.getY());
        int pointBMaxHeight = getMaxColumnHeightAtPoint((int)pointB.getX(), (int)pointB.getY());

        return Math.abs(pointAMaxHeight - pointBMaxHeight);
    }

    public Tile getTile(Point3D point){
        return tiles.get(point);
    }
}
