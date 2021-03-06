package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.Effect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Items.Item;
import Models.Map.MapUtilities.MapUtilities;
import Utilities.Constants;
import Utilities.MapUtilities.MapDrawingVisitor;
import Utilities.MapUtilities.MapNavigationUtilities;
import Utilities.Savable.Savable;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Map implements Savable {

    //// CLASS DECLARATIONS ////

    private HashMap<Point3D, Tile> tiles;
    private Set<Entity> entitiesOnMap;
    private ArrayList<Entity> storedEntities = new ArrayList();
    private Entity avatar;
    private Stack<Object> movementProcesses; // Keeps track of stuff thats trying to move. This must be empty to recenter!

    // Map will be passed the HashMap that is created by the gameloader after parsing the XML file.
    public Map(HashMap<Point3D, Tile> tiles){
        // Init properties
        this.tiles = tiles;
        this.entitiesOnMap = new LinkedHashSet<>();
        this.movementProcesses = new Stack<>();

        // Iterate over tile and add each entity to our set of entities.
        for (Tile tile : this.tiles.values()) {
            if (tile.containsEntity()) {
                this.entitiesOnMap.add(tile.getEntity());
            }
        }
    }

    //// MOVEMENT FUNCTIONS ////

    // The following things are being taken into consideration for movement:
    // If the height difference of desired tile is too tall, prevent movement,
    // If within one height difference allow movement, if there is a cliff, drop entity
    // to the bottom of the cliff.
    // Next, we check for an item (obstacle/interactive) or entity which will block movement.
    // Finally, we check the terrain type of the updated destination
    public void moveEntity(Entity entity, Point3D destination) {
        // Get the source tile.
        Point3D source = entity.getLocation();
        Tile sourceTile = tiles.get(source);
        // Update the destination point
        // This method will return the appropriate destination tile by checking all movement related factors.
        // See its comments for more info
        destination = updateDestinationPoint(destination, entity);

        final Tile updatedDestinationTile = tiles.get(destination);


        // Get the entites movement speed.
        int movementSpeed = entity.getStats().getStat(Stat.MOVEMENT);

        // Check if the tiles are in bounds of the map.
        if(sourceTile==null || updatedDestinationTile==null || destination==source || movementSpeed == 0){
            entity.failedMovement();
            return;
        }

        if (MapUtilities.distanceBetweenPoints(MapUtilities.to2DPoint(source),MapUtilities.to2DPoint(destination)) > 1) {
            entity.setLocation(destination);
            entity.setPixelLocation(updatedDestinationTile.getPixelPoint());
            updatedDestinationTile.removeEntity();
            updatedDestinationTile.insertEntity(entity);

            return;
        }


        // Move him at that rate, upon completion of translation, we will apply items/AoEs/etc on the tile.
        translateEntity(entity, destination, movementSpeed);
    }

    public void moveEntity(Entity entity, Direction direction){
        Point3D destination = direction.getPointAdjacentTo(entity.getLocation());
        moveEntity(entity, destination);
    }

    public void translateEntity(Entity entity, Point3D targetPoint3D, int pixelRate) {
        // Get entity's current point
        Point3D entityCurrentPoint = entity.getLocation();

        // Get target tile and entity tile
        Tile entityCurrentTile = tiles.get(entityCurrentPoint);
        Tile targetTile = tiles.get(targetPoint3D);

        // Get pixel points of each
        Point currentPixelLocation = entityCurrentTile.getPixelPoint();
        Point targetPixelLocation = targetTile.getPixelPoint();

        if(targetPixelLocation==null || currentPixelLocation==null){
            // BreakPOINT
            System.out.println("NOT A COMMENT");
            entity.moveComplete();
            return;
        }


        // Get x and y distances from entity's current point to the desired one.
        double dy = targetPixelLocation.getY() - currentPixelLocation.getY();
        double dx = targetPixelLocation.getX() - currentPixelLocation.getX();

        // Calculate the rate at which we increase the entity's y and x pixel values
        // While translating
        int xRate;
        int yRate;
        if (dx !=0 ) {
            xRate = dx > 0 ? pixelRate : -pixelRate;
        } else xRate = 0;
        if (dy !=0 ) {
            yRate = dy > 0 ? pixelRate : -pixelRate;
        } else yRate = 0;

        // If going diagonal, reduce yRate by 70%
        if (yRate != 0 && xRate != 0) {
            yRate = (int)(yRate*(0.70));
        }
        final int theYRate = yRate;


        // Create Timer and Periodic Timer Task and run it
        Timer entityMover = new Timer();
        TimerTask translateEntity = new TimerTask() {
            // Entity's final coord's which we will be manipulating
            // Upon every iteration
            int finalX = (int)currentPixelLocation.getX();
            int finalY = (int)currentPixelLocation.getY();
            Point finalPoint = new Point(finalX, finalY);

            @Override
            public void run() {

                // Increase x and y pixel values of entity.
                finalX += xRate;
                finalY += theYRate;

                // Set this new pixel point to the entity
                finalPoint.setLocation(finalX, finalY);
                entity.setPixelLocation(finalPoint);

                // If entity's pixel center entered the new tile,
                // Remove him from the old one and insert him onto the new.
                if (MapUtilities.isInBoundsOfTile(entity.getCenterPixelLocation(), targetTile)) {
                    // This if block should only be triggered once.
                    if (entity.enteredNewTile()) {
                       // Remove entity from its current tile
                        entityCurrentTile.removeEntity();
                        // Insert him at new tile
                        targetTile.insertEntity(entity);
                    }
                }

                // If we've reached the target pixel destination...
                if (MapUtilities.isAtTargetPoint(finalPoint, targetPixelLocation, xRate, theYRate)) {
                    // Stop executing
                    entityMover.cancel();
                    entityMover.purge();

                    // Set his logical and pixel location
                    entity.setLocation(targetPoint3D);
                    entity.setPixelLocation(targetPixelLocation);

                    // Tell the entity his move has completed!
                    entity.moveComplete();
                    movementProcesses.pop();

                    // Calculate if there needs to be fall damage
                    //Currently we only take fall damage if we fall more than 3 tiles
                    int heightDiff = (int)(entityCurrentPoint.getZ() - targetPoint3D.getZ());
                    if(heightDiff > 3){
                        System.out.println("You fell " + heightDiff + " tiles!");
                        double damage = Math.pow(1.5, heightDiff);
                        System.out.println("Damage taken: " + (int)damage);
                        StatModification fallDamageStatMod = new StatModification(Stat.HEALTH, (int)(-damage));
                        fallDamageStatMod.apply(entity.getStats());
                    }

                    // activate shit on the tile on him
                    boolean reRender = targetTile.activateTileObjectsOnEntity(entity);

                    if (!reRender) return;

                }
            }
        };

        // Translate the entity every ms
        movementProcesses.push(new Object());
        entityMover.scheduleAtFixedRate(translateEntity, 0, 100);
    }


    //// MAP MODIFIERS ////

    public void insertEntity(Entity entity, Point3D point){

        // Get the tile at the given point.
        Tile tile = tiles.get(point);

        // Insert the entity onto the tile.
        tile.insertEntity(entity);

        // Update the entity's location
        entity.setLocation(point);

        // Add the entity to the list of entites
        entitiesOnMap.add(entity);
    }

    public void moveEntityToNewTileAndRemoveFromOld(Entity entity, Point3D point) {
        Tile destination = tiles.get(point);
        Tile current = tiles.get(entity.getLocation());

        if(destination!=null){
            current.removeEntity();

            // insert
            destination.insertEntity(entity);

            entity.setLocation(point);
            entity.setPixelLocation(destination.getPixelPoint());
            entity.moveComplete();
            destination.activateTileObjectsOnEntity(entity);
        }
    }

    // This should only be used when an Entity is dead.
    public void removeEntity(Point3D point){

        // Get the tile at the given point.
        Tile tile = tiles.get(point);

        // Remove the entity at the tile.
        tile.removeEntity();

        // Remove the entity from the set of entities.
        removeEntityFromSetAtPoint(point);
    }

    private void removeEntityFromSetAtPoint(Point3D point){
        for(Iterator<Entity> iterator = entitiesOnMap.iterator(); iterator.hasNext();){
            Entity entity = iterator.next();

            if(entity.getLocation() == point){
                iterator.remove(); // By using an iterator, it is safe to remove the element while looping through.
            }
        }
    }

    //Testing this out
    public void storeOffMapEntity(Entity storedEntity){
        Tile tile = tiles.get(storedEntity.getLocation());
        tile.removeEntity();
        storedEntities.add(storedEntity);
        entitiesOnMap.remove(storedEntity);
    }

    public void addOffMapEntity(Entity storedEntity){

        for(Iterator<Entity> iterator = storedEntities.iterator(); iterator.hasNext();){
            Entity entity = iterator.next();

            if(entity.equals(storedEntity)){
                iterator.remove(); // By using an iterator, it is safe to remove the element while looping through.
            }
        }
        Point3D landingPoint = MapNavigationUtilities.findOpenTile(storedEntity,this);
       if(landingPoint != null) {
           storedEntity.setLocation(landingPoint);
           Tile tile = tiles.get(landingPoint);
           tile.insertEntity(storedEntity);
           entitiesOnMap.add(storedEntity);
           storedEntity.setPixelLocation(tile.getPixelPoint());
           tile.activateTileObjectsOnEntity(storedEntity);
       }
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

    public void draw(BufferedImage image,
                     Point3D center,
                     Point3D avatarLocation,
                     int rangeofVisibility ,
                     boolean cameraMvoing,
                     boolean debugMode) {
        MapDrawingVisitor.accept(tiles, image, center, avatarLocation, rangeofVisibility, cameraMvoing, debugMode, movementProcesses.empty());
    }

    //// MOVEMENT CHECKERS ////

    // Checks the destination tile for movement hindrance and height differences
    // Returns an updated destination point based on the above factos
    private Point3D updateDestinationPoint(Point3D originalDestinationPoint, Entity entity) {

        // Check if the tile is in bounds of the map.
        if( tiles.get(originalDestinationPoint)==null){
            return originalDestinationPoint;
        }

        // Get the entity's current point location before any movement
        Point3D entityCurrentLocation = entity.getLocation();

        // First, update the destination point based off of height differences
        // i.e: Too tall, fall off cliff, climb up 1 step, or move to a tile at the same height.
        //if you are a flyer you can disregard the updateDestinationPointBasedOnHeight method
        Point3D updatedDestinationPointBasedOffHeight = updateDestinationPointBasedOnHeight(originalDestinationPoint, entityCurrentLocation, entity);

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

    private Point3D updateDestinationPointBasedOnHeight(Point3D originalDestinationPoint, Point3D entityCurrentLocation, Entity entity) {
        // Compare height of dest. point, with entity's current height
        int tolerance = 1;
        int entityCurrentZ = (int)entityCurrentLocation.getZ();

        // Get the max column height at this x,y position.
        int destinationPointX = (int)originalDestinationPoint.getX();
        int destinationPointY = (int)originalDestinationPoint.getY();

        double destinationMaxZHeight = getMaxColumnHeightAtPoint(originalDestinationPoint);

//        System.out.println("THE MAX COLUMN HEIGHT AT THE TILE WERE ATTEMPTING TO MOVE AT IS ");
//        System.out.println(destinationMaxZHeight);

        // The column is too tall, block movement
        Tile oneAbove = getTile(originalDestinationPoint.add(0,0,1));
        if (destinationMaxZHeight > entityCurrentZ + tolerance && !entity.isFlyer()) {
            return entityCurrentLocation;
        }
        // If destination max Z is greater than entity's current z by one. we can move up one.
        // OR, if destination max Z is below entity's current Z: we are at a cliff.... need to drop down!!
        else if (destinationMaxZHeight - entityCurrentZ == tolerance || entityCurrentZ - destinationMaxZHeight > 0 && !entity.isFlyer()){
            return new Point3D(destinationPointX, destinationPointY, destinationMaxZHeight);
        }

        else if(oneAbove!= null && oneAbove.getTerrain() == Terrain.EARTH){
           return entityCurrentLocation;
        }
        // Else, If on the same height level, allow normal movement
        // ^ entityCurrentZ == destinationMaxZHeight ^
        else  {
            return originalDestinationPoint;
        }
    }

    //// HELPERS ////

    public int getMaxColumnHeightAtPoint(Point3D point) {
        int x = (int)point.getX();
        int y = (int)point.getY();

        // Start at the current Z point
        int originalZ = (int)point.getZ();
        int z = originalZ;

        // Get tile and point
        Point3D pointToCheck = new Point3D(x, y, z);
        Tile tileToCheck = tiles.get(pointToCheck);

        // While we aren't at sky keep incrementing z and checking
        while (z < Constants.COLUMN_HEIGHT && tileToCheck.getTerrain() != Terrain.SKY) {
            z++;
            pointToCheck = new Point3D(x, y, z);
            tileToCheck = tiles.get(pointToCheck);
        }

        // Means, we need to look down
        if (z == originalZ) {
            while (z > 0 && tileToCheck.getTerrain() != Terrain.EARTH ) {
                z--;
                pointToCheck = new Point3D(x, y, z);
                tileToCheck = tiles.get(pointToCheck);
            }
            return z;
        } else {
            return z-1;
        }


    }

    public int getColumnHeightDifferenceBetweenPoints(Point3D pointA, Point3D pointB) {
        int pointAMaxHeight = getMaxColumnHeightAtPoint(pointA);
        int pointBMaxHeight = getMaxColumnHeightAtPoint(pointB);

        return Math.abs(pointAMaxHeight - pointBMaxHeight);
    }

    public Tile getTile(Point3D point){
        return tiles.get(point);
    }

    public void setAvatar(Entity avatar){
        this.avatar = avatar;
    }

    public Entity getAvatar(){
        return avatar;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        //determine true 2D dimensions of the map (10 tiles high always!)
        int size = (int)Math.sqrt(tiles.size()/10);
        String dimension = String.valueOf(size);

        //create map element
        Element mapElement = doc.createElement("map");

        //add the maps dimensions as attributes
        mapElement.setAttribute("height", dimension);
        mapElement.setAttribute("width", dimension);

        //add to the document
        parentElement.appendChild(mapElement);

        //iterate through all the tiles on the map and save them
        for (java.util.Map.Entry<Point3D,Tile> entry: tiles.entrySet()) {

            //create tile element
            Element tile = doc.createElement("tile");

            //get the tiles point and tile
            Point3D p = entry.getKey();
            Tile t = entry.getValue();

            //save the location of the tile as attributes
            tile.setAttribute("x", String.valueOf((int) p.getX()));
            tile.setAttribute("y", String.valueOf((int) p.getY()));
            tile.setAttribute("z", String.valueOf((int) p.getZ()));

            //add the tile to the map
            mapElement.appendChild(tile);

            t.save(doc, tile);
        }

        return doc;
    }

    @Override
    public void load(Element data) {
        try {
            // Get the tilesNodes from the xml file
            NodeList tileNodes = data.getElementsByTagName("tile");

            //find out how many tiles there are
            int numTiles = tileNodes.getLength();

            //instantiate every tile to the map
            for(int i=0; i<numTiles; i++) {

                Element tileElement = (Element) tileNodes.item(i);

                int x = Integer.parseInt(tileElement.getAttribute("x"));
                int y = Integer.parseInt(tileElement.getAttribute("y"));
                int z = Integer.parseInt(tileElement.getAttribute("z"));

                //construct an empty tile and load it into the game
                Tile tile = new Tile();
                tile.load(tileElement);


                // Check to see if this column has already been started
                this.tiles.put(new Point3D(x, y, z), tile);
                if(tile.containsEntity()) {
                    insertEntity(tile.getEntity(), new Point3D(x, y, z));
                    tile.getEntity().setMap(this);
                    System.out.println(tile.getEntity().getOccupation().toString());
                    System.out.println(tile.getEntity().isAvatar());
                    if(tile.getEntity().isAvatar())
                        setAvatar(tile.getEntity());
                }
            }


        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }
    }

    public HashMap<Point3D, Tile> getTiles(){
        return tiles;
    }

    public Set<Entity> getEntitiesOnMap(){
        return entitiesOnMap;
    }
}
