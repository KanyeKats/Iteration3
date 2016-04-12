package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.Effect;
import Models.Items.Item;
import Models.Map.AreaEffects.AreaEffectFactory;
import Models.Map.AreaEffects.RiverAreaEffect;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.print.Doc;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import Utilities.MapUtilities.TileDrawingVisitor;

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
    // TODO: User visitor pattern to construct tile image?
    // pixel point used for moving
    private Point pixelPoint;

    public Tile(Terrain terrain, AreaEffect areaEffect, Entity entity, ArrayList<Item> items, Decal decal, Effect effect){
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.entity = entity;
        this.items = items;
        this.decal = decal;
        this.effect = effect;
    }

    public Tile() {
        this.terrain = null;
        this.areaEffect = null;
        this.entity = null;
        this.items = new ArrayList<>();
        this.decal = null;
        this.effect = null;
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

    public void activateTileObjectsOnEntity(Entity entity) {
        // Activate items
        for (Iterator<Item> iterator = items.iterator(); iterator.hasNext(); ) {
            Item item = iterator.next();

            // Activate the item and see if it should be removed from the map after its activation.
            boolean removeItem = item.onTouch(entity);
            if (removeItem) {
                iterator.remove();
            }
        }

        // Activate AreaEffects
        if(areaEffect!=null){
            areaEffect.activate(entity);
        }
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

    public void insertEffect(Effect effect){ this.effect = effect; }

    public void removeEffect() { this.effect = null; }

    public Terrain getTerrain() { return this.terrain; }

    public Image acceptDrawingVisitor(TileDrawingVisitor visitor){
        return visitor.accept(this);
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
//        areaEffect.save(doc, parentElement);

        //save Decal
//        decal.save(doc, parentElement);

        //save Items
//        Element itemsElement = doc.createElement("items");
//        itemsElement.setAttribute("itemAmt", String.valueOf(items.size()));
//        for (Item item : items) {
//            item.save(doc, parentElement);
//        }
//        parentElement.appendChild(itemsElement);

        //save Entity
//        entity.save(doc, parentElement);

        //save effect
//        effect.save(doc, parentElement);

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
        } else {
            this.areaEffect = null;
        }


        // Get the item child nodes of the tile
        NodeList itemNodes = tileElement.getElementsByTagName("item");

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


        // TODO: Implement these functions
        this.decal = null;
        this.entity = null;
        this.effect = null;

        // Add rivers whereever water is.
        if (terrain == terrain.WATER) {
            this.areaEffect = new RiverAreaEffect(Direction.SOUTH_EAST, 35);
        }
    }
}
