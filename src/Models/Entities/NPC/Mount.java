package Models.Entities.NPC;

import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Inventory;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.Occupation.Smasher;
import Models.Entities.Occupation.Sneak;
import Models.Entities.Occupation.Summoner;
import Models.Entities.Occupation.Vehicle;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Utilities.Savable.Savable;
import Views.Graphics.Assets;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Mount extends NPC implements Savable{

    private Entity driver;
    private int entityPrevspeed;

    //For saving/loading purposed
    public Mount(){
        super();
    }

    public Mount(Point3D location, Map map, Terrain[] passableTerrain, Boolean isFlyer) {
        super(new Vehicle(), location, map, passableTerrain, Personality.MOUNT, isFlyer);
        setMountImages();
        this.driver = null;
    }

    @Override
    public void interacted(Entity entity) {
        mount(entity);
    }

    public void mount(Entity driver) {
        if (this.driver == null) {
            this.driver = driver;
            entityPrevspeed = driver.mountVehicle(this);
            getMap().storeOffMapEntity(driver);
        }
    }

    public void unMount(Entity driver){
        if(driver != null){
            getMap().addOffMapEntity(driver);
            this.driver = null;
        }
    }

    public Entity getDriver() {
        return driver;
    }

    public void setMountImages(){
        HashMap<Direction,BufferedImage> images = getImages();
        images.clear();
        images.put(Direction.NORTH, Assets.MOUNT_NORTH);
        images.put(Direction.NORTH_EAST, Assets.MOUNT_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.MOUNT_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.MOUNT_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.MOUNT_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.MOUNT_NORTH_WEST);
    }

    public int getEntityPrevspeed() {
        return entityPrevspeed;
    }

    public void setEntityPrevspeed(int entityPrevspeed) {
        this.entityPrevspeed = entityPrevspeed;
    }

    public Document save(Document doc, Element parentElement) {
        Element entity = doc.createElement("mount");
        String locString = Integer.toString((int)getLocation().getX()) + ",";
        locString += Integer.toString((int)getLocation().getY()) + ",";
        locString += Integer.toString((int)getLocation().getZ());
        entity.setAttribute("location", locString);
        entity.setAttribute("direction", String.valueOf(getDirection()));
        entity.setAttribute("is-mounted", String.valueOf(isMounted()));
        entity.setAttribute("is-flyer", String.valueOf(isFlyer()));
        parentElement.appendChild(entity);

        //save inventory
        if(this.getInventory() != null) {
            Element inventory = doc.createElement("inventory");
            inventory.setAttribute("capacity", Integer.toString(this.getInventory().getCapacity()));
            entity.appendChild(inventory);

            this.getInventory().save(doc, inventory);
        }

        //save equipment
        if(this.getEquipment() != null) {
            Element equipment = doc.createElement("equipment");
            entity.appendChild(equipment);

            this.getEquipment().save(doc, equipment);
        }

        //save occupation
        if(this.getOccupation() != null) {
            Element occupation = doc.createElement("occupation");
            occupation.setAttribute("value", this.getOccupation().toString());
            entity.appendChild(occupation);
        }

        //save stats
        if(this.getStats() != null){
            Element stats = doc.createElement("stats");
            entity.appendChild(stats);
            this.getStats().save(doc, stats);
        }

        //save active skills
        if(this.getActiveSkillList() != null){
            Element activeSkillList = doc.createElement("active-skill-list");
            entity.appendChild(activeSkillList);
            this.getActiveSkillList().save(doc,activeSkillList);
        }

        //save passive skills
        if(this.getPassiveSkillList() != null){
            Element passiveSkillList = doc.createElement("passive-skill-list");
            entity.appendChild(passiveSkillList);
            this.getPassiveSkillList().save(doc,passiveSkillList);
        }

        //save passable terrains
        if(this.getPassableTerrains() != null){
            Element passable = doc.createElement("passable-terrains");
            entity.appendChild(passable);
            for(Terrain t : getPassableTerrains()){
                Element terrain = doc.createElement("terrain");
                terrain.setAttribute("type", t.name());
                passable.appendChild(terrain);
            }
        }


        return doc;
    }


    @Override
    public void load(Element data) {

        //Load in occupation
        NodeList occupationNodes = data.getElementsByTagName("occupation");
        if(occupationNodes.getLength() != 0) {
            Node node = occupationNodes.item(0);
            Element inventoryElement = (Element) node;
            String occupationString = inventoryElement.getAttribute("value");
            if (occupationString.equals("Smasher"))
                setOccupation(new Smasher());
            else if (occupationString.equals("Summoner"))
                setOccupation(new Summoner());
            else
                setOccupation(new Sneak());
        }
        else{
            setOccupation(new Smasher());
        }


        //Load in stats
        NodeList statNodes = data.getElementsByTagName("stats");
        if(statNodes.getLength() != 0) {
            Node node = statNodes.item(0);
            Element statElement = (Element) node;
            setStats(new Stats());
            getStats().load(statElement);
        }
        else{
            setStats(new Stats());
            getOccupation().initStats(getStats());
        }


        //Load in inventory
        NodeList inventoryNodes = data.getElementsByTagName("inventory");
        if(inventoryNodes.getLength() != 0){
            Node node = inventoryNodes.item(0);
            Element inventoryElement = (Element) node;
            String capacityString = inventoryElement.getAttribute("capacity");
            int capacity = Integer.parseInt(capacityString);
            setInventory(new Inventory(capacity));
            getInventory().load(inventoryElement);
        }
        else {
            setInventory(new Inventory(20));
        }


        //Load in active skills
        NodeList activeSkillNodes = data.getElementsByTagName("active-skill-list");
        if(activeSkillNodes.getLength() != 0){
            Node node = activeSkillNodes.item(0);
            Element activeSkillsElement = (Element) node;
            setActiveSkillList(getOccupation().initActiveSkills(getStats()));
            getActiveSkillList().load(activeSkillsElement);
        }
        else {
            setActiveSkillList(getOccupation().initActiveSkills(getStats()));
        }


        //Load in passive skills
        NodeList passiveSkillNodes = data.getElementsByTagName("passive-skill-list");
        if(passiveSkillNodes.getLength() != 0){
            Node node = passiveSkillNodes.item(0);
            Element passiveSkillsElement = (Element) node;
            setPassiveSkillList(getOccupation().initPassiveSkills(getStats()));
            getPassiveSkillList().load(passiveSkillsElement);
        }
        else {
            setPassiveSkillList(getOccupation().initPassiveSkills(getStats()));
        }


        // Load in equipment
        NodeList equipmentNodes = data.getElementsByTagName("equipment");
        if(equipmentNodes.getLength() != 0){
            Node node = equipmentNodes.item(0);
            Element equipmentElement = (Element) node;
            setEquipment(new Equipment(getStats(), getInventory()));

            NodeList itemNodes = equipmentElement.getElementsByTagName("item");
            if (itemNodes.getLength() != 0) {
                // Get all item elements
                for (int i = 0; i < itemNodes.getLength(); i++) {
                    // Get the node/element
                    Node itemNode = itemNodes.item(i);
                    Element itemElement = (Element) itemNode;

                    // Grab the item id
                    String itemID = itemElement.getAttribute("id");
                    int id = Integer.parseInt(itemID);

                    // Construct an instance and add it to this inventory
                    EquippableItem item = (EquippableItem) Item.itemFromID(id);
                    equip(item);
                }
            }
        }
        else {
            setEquipment(new Equipment(getStats(), getInventory()));
        }


        //Load in passable terrains
        NodeList passableTerrainNode = data.getElementsByTagName("passable-terrains");
        if(passableTerrainNode.getLength() != 0){
            Node node = passableTerrainNode.item(0);


            Element terrainElement = (Element) node;
            NodeList terrainNodes = terrainElement.getElementsByTagName("terrain");
            if(terrainNodes.getLength() != 0){
                setPassableTerrains(new ArrayList<>());
                for(int i = 0; i < terrainNodes.getLength(); i++) {
                    Element terrain = (Element) terrainNodes.item(i);
                    this.getPassableTerrains().add(Terrain.valueOf(terrain.getAttribute("type")));
                }
            }
        }
        else {
            setPassableTerrains(null);
        }


        //Load in location
        String locationString = data.getAttribute("location");
        String[] stringArray = locationString.split(",");
        int x = Integer.parseInt(stringArray[0]);
        int y = Integer.parseInt(stringArray[1]);
        int z = Integer.parseInt(stringArray[2]);
        setLocation(new Point3D(x,y,z));


        //Load in direction
        String directionString = data.getAttribute("direction");
        setDirection(Direction.valueOf(directionString));


        //Load in isFlyer
        String flyerString = data.getAttribute("is-flyer");
        setFlyer(Boolean.valueOf(flyerString));

        setImages(getOccupation().initImages());
        setMountImages();
        setVisible(true);               //TODO: Save/load this value
        setCanMove(true);
    }
}
