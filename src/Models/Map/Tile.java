package Models.Map;

import Models.Entities.Entity;
import Models.Entities.NPC.Mount;
import Models.Entities.NPC.NPC;
import Models.Entities.Skills.InfluenceEffect.Effect;
import Models.Items.Item;
import Models.Map.AreaEffects.AreaEffectFactory;
import Models.Map.AreaEffects.RiverAreaEffect;
import Utilities.MapUtilities.TileDrawingVisitor;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Tile implements Savable {

    private Terrain terrain;
    private AreaEffect areaEffect;
    private Decal decal;
    private Entity entity;
    private ArrayList<Item> items;
    private Effect effect;
    private Boolean visited;
    // pixel point used for moving
    private Point pixelPoint;

    // TODO: Remember to notify observers whenever you do something to a tile that modifies how it will be displayed (and youre not going through the map to do it).
    public Tile(Terrain terrain, AreaEffect areaEffect, Entity entity, ArrayList<Item> items, Decal decal, Effect effect){
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.entity = entity;
        this.items = items;
        this.decal = decal;
        this.effect = effect;
        visited = false;
    }

    public Tile() {
        this.terrain = null;
        this.areaEffect = null;
        this.entity = null;
        this.items = new ArrayList<>();
        this.decal = null;
        this.effect = null;
        this.visited = false;
    }

    public boolean containsEntity(){
        return entity!=null;
    }

    public boolean containsVisibleEntity(){
        return entity!=null && entity.isVisible();
    }

    public boolean preventsMovement(Entity entityAttemptingMovement) {

        // Check if an entity prevents movement
        if (this.containsEntity()) {
            return true;
        }

        // Check if an item prevents movement
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
            Item item = iterator.next();
            if (item.preventsMovement(entityAttemptingMovement)) {
                return true;
            }
        }

        // If none of the above prevents movement, return false
        return false;
    }

    public void insertEntity(Entity entity) {
        this.entity = entity;
    }

    public boolean activateTileObjectsOnEntity(Entity entity) {
        // Activate items
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();

            // Activate the item and see if it should be removed from the map after its activation.
            boolean removeItem = item.onTouch(entity);
            if (removeItem) {
                iterator.remove();
            }
            return true;
        }

        // Activate AreaEffects
        if(areaEffect!=null) {
            areaEffect.activate(entity);
            return true;
        }
        return false;
    }

    public void removeEntity(){
        entity = null;
    }

    public void insertItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void insertAreaEffect(AreaEffect areaEffect){
        this.areaEffect = areaEffect;
    }

    public void removeAreaEffect(){
        this.areaEffect = null;
    }

    public void insertEffect(Effect effect){
        this.effect = effect;
    }

    public void removeEffect() { this.effect = null;
    }

    public Terrain getTerrain() { return this.terrain; }

    public Image acceptDrawingVisitor(TileDrawingVisitor visitor, boolean isInSight){
        return visitor.accept(this,isInSight);
    }

    public Entity getEntity() {
        return entity;
    }

    public Image getEntityImage(){
        return entity!=null ? entity.getImage() : null;
    }

    public AreaEffect getAreaEffect() { return this.areaEffect; }

    public ArrayList<Item> getItems() { return this.items; }

    public Effect getEffect() { return this.effect; }

    public Boolean wasVisited() { return this.visited; }

    public void setVisited() { this.visited = true; }

    public Point getPixelPoint() {
        return pixelPoint;
    }

    public void setPixelPoint(Point pixelPoint) {
        this.pixelPoint = pixelPoint;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        //save terrain
        Element terrain = doc.createElement("terrain");
        terrain.setAttribute("type", getTerrain().name());
        parentElement.appendChild(terrain);

        //save areaEffect
        if(areaEffect != null)
            areaEffect.save(doc, parentElement);

        //save Items
        for (Item item : items) {
            item.save(doc, parentElement, "tile-item");
        }

        //save Entity
        if(this.entity != null) {
            this.entity.save(doc, parentElement);
        }

        //return the tile element
        return doc;
    }

    @Override
    public void load(Element tileElement) {

        // Get single terrain node per tile
        NodeList terrainNode = tileElement.getElementsByTagName("terrain");

        // Get terrain element
        Element terrainElement = (Element) terrainNode.item(0);

        // Add terrain to tile
        this.terrain = terrain.valueOf(terrainElement.getAttribute("type"));


        // Get the aoe child node of the tile
        NodeList aoeNodes = tileElement.getElementsByTagName("area-effect");

        // Check if this tile has an Area Effect by checking if the NodeList is not empty
        if (aoeNodes.getLength() != 0) {
            // Get the actual aoe element-node
            Element aoeElement = (Element) aoeNodes.item(0);

            // Grab
            // the type and value
            String aoeType = aoeElement.getAttribute("type");
            String aoeValue = aoeElement.getAttribute("value");

            // Create instance from AreaEffect factory and assign to this tile
            this.areaEffect = AreaEffectFactory.valueOf(aoeType).createInstance(aoeValue);
        }
        else {
            this.areaEffect = null;
        }



        //Load items
        // Get the item child nodes of the tile
        NodeList itemNodes = tileElement.getElementsByTagName("tile-item");

        this.items = new ArrayList<>();
        // Check if this tile has an Area Effect by checking if the NodeList is not empty
        if (itemNodes.getLength() != 0) {
            // Get all item elements
            for (int i = 0; i < itemNodes.getLength(); i++) {
                // Get the node/element
                Node node = itemNodes.item(i);
                Element itemElement = (Element) node;

                // Grab the item id
                String itemID = itemElement.getAttribute("id");
                int id = Integer.parseInt(itemID);

                // Construct an instance and add it to this tile's list of items.
                Item item = Item.itemFromID(id);
                this.items.add(item);
            }
        }

        //Load entity
        NodeList entityNodes = tileElement.getElementsByTagName("entity");
        NodeList mountNodes = tileElement.getElementsByTagName("mount");
        NodeList npcNodes = tileElement.getElementsByTagName("npc");
        if(entityNodes.getLength() != 0){
            for(int i = 0; i < entityNodes.getLength(); i++){
                Node node = entityNodes.item(i);
                Element entityElement = (Element) node;
                entity = new Entity();
                entity.load(entityElement);
            }
        }
        else if(mountNodes.getLength() != 0){
            for(int i = 0; i < mountNodes.getLength(); i++){
                Node node = mountNodes.item(i);
                Element mountElement = (Element) node;
                entity = new Mount();
                entity.load(mountElement);
            }
        }
        else if(npcNodes.getLength() != 0){
            for(int i = 0; i < npcNodes.getLength(); i++){
                Node node = npcNodes.item(i);
                Element npcElement = (Element) node;
                entity = new NPC();
                entity.load(npcElement);
            }
        }
        else{
            this.entity = null;
        }

        // Add rivers wherever water is.
        if (terrain == terrain.WATER) {
            this.areaEffect = new RiverAreaEffect(Direction.SOUTH_EAST, 35);
        }
    }
}
