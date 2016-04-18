package Models.Entities.NPC;

import Models.Consequences.BehaviorConsequence;
import Models.Consequences.SleepConsequence;
import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Inventory;
import Models.Entities.NPC.AI.Brain.Brain;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Occupation.Smasher;
import Models.Entities.Occupation.Sneak;
import Models.Entities.Occupation.Summoner;
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
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Aidan on 4/6/2016.
 */
public class NPC extends Entity implements Savable{

    //needs a brain and such
    private Brain brain;

    //For saving/loading purposes
    public NPC(){}

    public NPC(Occupation occupation, Point3D location, Map map, Terrain[]passableTerrain, Personality personality, Boolean isFlyer) {
        super(occupation, location, map, isFlyer, passableTerrain);
        this.brain = new Brain(personality, this);
    }

    @Override
    public void update() {
        super.update();
        if(brain != null)
            brain.think();
    }

    public String getDialog() { return brain.getDialog(); }

    @Override
    public void interacted(Entity entity){

    }

    @Override
    public void makeSleep() {
        brain.setIsSleeping(true);
    }

    @Override
    public void wakeUp(){
        brain.setIsSleeping(false);
    }

    @Override
    public void fear(Direction direction){
        brain.getFrontalLobe().setisFeared(true,this.getDirection(),this);
        brain.getVisualInfo().setStillFeared(true);
    }

    @Override
    public void dontFear(){
        brain.getFrontalLobe().setisFeared(false,null,this);
        brain.getVisualInfo().setStillFeared(false);
    }

    @Override
    public HashMap<Direction,BufferedImage>  polymorph() {
        //need to save the previous images
        //i let the consequence hold the images and give them back
        //to the entity when the polymorph is over
        HashMap<Direction,BufferedImage> tempImages = new HashMap<>();
        HashMap<Direction,BufferedImage> images = this.getImages();
        for(Direction direction : images.keySet()){
            BufferedImage image = images.get(direction);
            tempImages.put(direction,image);
        }
        brain.getFrontalLobe().actLikeFrog(true);
        //TODO: Make frog images not bug
        images.put(Direction.NORTH, Assets.BUG_NORTH);
        images.put(Direction.NORTH_EAST, Assets.BUG_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.BUG_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.BUG_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.BUG_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.BUG_NORTH_WEST);
        return tempImages;
    }

    @Override
    public void changeBack(HashMap<Direction,BufferedImage> images) {
        brain.getFrontalLobe().actLikeFrog(false);
        setImages(images);
    }

    public boolean willTrade() { return brain.willTrade(); }

    public Document save(Document doc, Element parentElement) {

        Element entity = doc.createElement("npc");
        String locString = Integer.toString((int)getLocation().getX()) + ",";
        locString += Integer.toString((int)getLocation().getY()) + ",";
        locString += Integer.toString((int)getLocation().getZ());
        entity.setAttribute("location", locString);
        entity.setAttribute("direction", String.valueOf(getDirection()));
        entity.setAttribute("is-mounted", String.valueOf(isMounted()));
        entity.setAttribute("is-avatar", String.valueOf(isAvatar()));
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

        //save brain
        if(this.brain != null){
            Element brain = doc.createElement("brain");
            brain.setAttribute("personality", String.valueOf(this.brain.getPersonality()));
            entity.appendChild(brain);
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
        setVisible(true);
        setCanMove(true);


        //Load in brain
        NodeList brainNodes = data.getElementsByTagName("brain");
        if(brainNodes.getLength() != 0){
            Node node = brainNodes.item(0);
            Element brainElement = (Element) node;
            this.brain = new Brain(Personality.valueOf(brainElement.getAttribute("personality")), this);
        }
        else{
            this.brain = new Brain(Personality.FRIENDLY, this);
        }
    }
}
