package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.Effects.Effect;
import Models.Items.Item;
import Models.Map.MapUtilities.MapDrawingVisitor;
import Utilities.Savable.Savable;
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
import java.util.Observable;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Map extends Observable implements Savable {

    private HashMap<Point3D, Tile> tiles;

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

        // Get the destination tile.
        Tile destinationTile = tiles.get(destination);

        // Get the source tile.
        Point3D source = entity.getLocation();
        Tile sourceTile = tiles.get(source);

        // Check if the tiles are in bounds of the map.
        if(source==null || destination==null){
            return;
        }

        // TODO: Implement movement. The following things will need to be done.
        // TODO: 1) Check if there is an obstacle or interactive item that will prevent movement.
        // TODO:    This can be done with tile.getItem().preventsMovement(); This violates LOD though.
        // TODO: 2) Check fi there is an entity on the same tile already.
        // TODO: 3) Check the terrain type (if its water, and also eleveation differences
        // TODO:    If the desired tile is too high, prevent movement, if it is lower, fall to the earth and deal damage.

        // NOTE: The activation of area effects and items is the responsibility of the tile once it moves onto it.

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

    @Override
    public String save() {
        return null;
    }

    @Override
    public void load(String data) {
        try {
            // Create a document from the xml file
            DocumentBuilderFactory documentBuilderFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = documentBuilderFactory.newDocumentBuilder();
            Document doc = docBuilder.parse(new File(filepath));

            // Normalize
            doc.getDocumentElement().normalize();

            NodeList mapList = doc.getElementsByTagName("map");
            Element mapElement = (Element) mapList.item(0);


            // Create empty tiles
            HashMap<Point3D, Tile> tiles = new HashMap<>();

            // Get the tilesNodes from the xml file.
            NodeList tileNodes = mapElement.getElementsByTagName("tile");
            int numTiles = tileNodes.getLength();

            for(int i=0; i<numTiles; i++){

                Element tileElement = (Element) tileNodes.item(i);

                int x = Integer.parseInt(tileElement.getAttribute("x"));
                int y = Integer.parseInt(tileElement.getAttribute("y"));
                int z = Integer.parseInt(tileElement.getAttribute("z"));

                // Create the terrain
                Terrain terrain = getTerrain(tileElement);

                // TODO: Implement these functions
                AreaEffect areaEffect = null;
                Decal decal = null;
                ArrayList<Item> items = null;
                Entity entity = null;
                Effect effect = null;

                // Check to see if this column has already been started
                tiles.put(new Point3D(x, y, z), new Tile(terrain , areaEffect, entity, items, decal, effect));
            }

            return new Map(tiles);

        } catch (SAXParseException e) {
            System.out.println("Error parsing");
            e.printStackTrace();
        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }
    }
}
