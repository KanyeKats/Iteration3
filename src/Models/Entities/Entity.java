package Models.Entities;

import Models.Entities.NPC.Mount;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Occupation.Smasher;
import Models.Entities.Occupation.Sneak;
import Models.Entities.Occupation.Summoner;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Items.Takable.TakableItem;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Models.Map.Tile;
import Utilities.Savable.Savable;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Entity implements Savable {
    private Occupation occupation;
    private Stats stats;
    private ActiveSkillList activeSkillList;
    private PassiveSkillList passiveSkillList;
    private Inventory inventory;
    private Equipment equipment;
    private BufferedImage sprite;
    private boolean isVisible;
    private Point3D location;
    private Point pixelLocation;
    private Direction direction;
    private Map map;
    private HashMap<Direction, BufferedImage> images;
    private boolean canMove;
    private boolean justMoved;
    private boolean enteredNewTile;
    private boolean isAvatar = false;
    private Timer movementTimer;
    private boolean isMounted;
    private boolean isFlyer;
    private Mount mount;
    private Direction pendingMovement;

    // TODO: Ask about terrain checking... not sure if this is ok
    private ArrayList<Terrain> passableTerrains;


    // TODO: Whenever something changes in the entity that would change its apperance, make sure to call setChanged() notifyObservers();

    // This is used when loading a saved game
    public Entity() {
        this.isVisible = true;          //TODO: Save/load this value
        movementTimer = new Timer();
        canMove = true;
        justMoved = false;
        enteredNewTile = false;
    }

    public Entity(Occupation occupation, Stats stats, Inventory inventory, Equipment equipment, BufferedImage sprite, Point3D location, Direction orientation, Map map, Boolean isFlyer){


        // TODO: Make sure that instantiating an entity with existing inventorys and equipment, make sure that the Equipemnt has a correct reference to the inventory.
        this.occupation = occupation;
        this.stats = stats;
        this.inventory = inventory;
        this.equipment = equipment;
        this.sprite = sprite;
        this.location = location;
        this.direction = orientation;
        this.map = map;
        isVisible = true;
        occupation.initStats(this.stats);
        activeSkillList = occupation.initActiveSkills(stats);
        passiveSkillList = occupation.initPassiveSkills(stats);
        images = occupation.initImages();
        this.isMounted = false;
        this.isFlyer = isFlyer;
        this.pendingMovement = null;
    }

    public Entity(Occupation occupation, Point3D location, Map map,Boolean isFlyer, Terrain... passableTerrains ){
        this.occupation = occupation;
        this.location = location;
        this.stats = new Stats();
        this.inventory = new Inventory(20);
        this.equipment = new Equipment(stats, inventory);
        this.sprite = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        this.direction = Direction.SOUTH;
        this.map = map;
        this.passableTerrains = new ArrayList<>(Arrays.asList(passableTerrains));
        isVisible = true;
        occupation.initStats(this.stats);
        activeSkillList = occupation.initActiveSkills(stats);
        passiveSkillList = occupation.initPassiveSkills(stats);
        images = occupation.initImages();
        this.pendingMovement = null;

        //initImages();

        // Set movment variables
        movementTimer = new Timer();
        canMove = true;
        justMoved = false;
        enteredNewTile = false;

        this.isMounted = false;
        this.isFlyer = isFlyer;


    }

    public void equip(EquippableItem item){
        // Only equip the item if this instance of entity fufills the stat requirement to equip the item.
        if (item.fufillEquipRequirement(this)) {
            boolean successfullEquip = item.equip(equipment, passiveSkillList);
            if (successfullEquip) {
                inventory.removeItem(item);
            }
        }
    }

    private void equipAllAfterSave(ArrayList<EquippableItem> items){
        for(EquippableItem item : items){
            if(item != null) {
                boolean successfullEquip = item.equip(equipment, passiveSkillList);
                if (successfullEquip) {
                    inventory.removeItem(item);
                }
            }
        }
    }

    public void unequip(EquippableItem item){
        item.unequip(equipment);
    }

    private ArrayList<EquippableItem> unequipAllForSave(){
        ArrayList<EquippableItem> items = new ArrayList<>();
        items.add(equipment.unequipBoots());
        items.add(equipment.unequipBothHands());
        items.add(equipment.unequipChest());
        items.add(equipment.unequipHead());
        items.add(equipment.unequipLeftHand());
        items.add(equipment.unequipLeggings());
        items.add(equipment.unequipRightHand());

        return items;
    }

    public final void move(Direction direction) {
        // Move with taking movement speed in to account
        if(isMounted){
            pendingMovement = null;
            mount.move(direction);
            setLocation(mount.getDirection().getPointAdjacentTo(mount.getLocation()));
        }
        else if (canMove) {
            // Don't allow the entity to move
            canMove = false;
            pendingMovement = null;

            // Move the entity
            if(direction != Direction.UP && direction != Direction.DOWN){
                this.direction = direction;
            }
            map.moveEntity(this, direction);

        }else{
            pendingMovement = direction;
        }
    }
    public final void moveComplete() {
        this.justMoved = true;
        this.enteredNewTile = false;

        // Allow movement again
        movementTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                Entity.this.canMove = true;
                if(pendingMovement!=null){
                    move(pendingMovement);
                }

            }
        }, 300);
    }

    public final boolean enteredNewTile() {
        if (!enteredNewTile) {
            enteredNewTile = true;
            return true;
        }
        return false;
    }

    public final void failedMovement() {
        this.canMove = true;
        this.justMoved = false;
    }

    // Not a mistake, I think it will be good to have overloaded move methods
    // The default will be moving a direction, but sometimes we want to move to a desired point instantly.
    // For example, teleporting, falling off a cliff etc
    public final void move(Point3D desiredPoint) {
        map.moveEntityToNewTileAndRemoveFromOld(this, desiredPoint);
    }

    //Entities arent in charge of adding items to themselves right hmmm or does tile call entity.add(item)?
    public boolean addItemToInventory(TakableItem item){
        return inventory.addItem(item);
    }

    public void dropItem(int positionInInventory) {
        // Remove the item from the inventory
        TakableItem item = this.inventory.removeItemAtIndex(positionInInventory);
        dropItem(item);
    }

    public void dropItem(TakableItem item) {
        // Drop it like its hot
        if (item != null) {
            map.insertItem(item, location);
        }
    }


    public void dropAllItems() {
        // Get inventory
        Inventory inventory = this.getInventory();

        // Get and remove all items
        ArrayList<TakableItem> items = inventory.getAndRemoveAllItems();

        // Drop em all like its hot
        for (TakableItem item : items) {
            dropItem(item);
        }
    }


    public void useSkill(Skill skill){

    }

    public int mountVehicle(Mount mount){
        this.mount= mount;
        int prevEntityMovement = getStats().getStat(Stat.MOVEMENT);
        stats.setStat(Stat.MOVEMENT,mount.getStats().getStat(Stat.MOVEMENT));
        isMounted = true;
        return prevEntityMovement;

    }

    public void unMountVehicle(){
        stats.setStat(Stat.MOVEMENT,mount.getEntityPrevspeed());
        isMounted = false;
        mount.unMount(this);
        this.mount = null;
    }

    public boolean isMounted(){
        return this.isMounted;
    }

    public Mount getMount(){
        return this.mount;
    }

    public void die(){

    }

    public void revive(){

    }

    //Trying this out -Aidan
    public void interact(){
        Entity entity = getTileInFront().getEntity();
        if(entity != null){
            entity.interacted(this);
        }
    }

    public void interacted(Entity entity){
        System.out.println("hey dont touch me!");
    }

    //Getters and Setters start here
    public Occupation getOccupation() {
        return occupation;
    }

    public void setOccupation(Occupation occupation) {
        this.occupation = occupation;
    }

    public Stats getStats() {
        return stats;
    }

    public void setStats(Stats stats) {
        this.stats = stats;
    }

    public ActiveSkillList getActiveSkillList() {
        return activeSkillList;
    }

    public void setCanMove(boolean canMove) {
        this.canMove = canMove;
    }

    public boolean getCanMove() { return this.canMove; }

    public void setActiveSkillList(ActiveSkillList activeSkillList) {
        this.activeSkillList = activeSkillList;
    }

    public PassiveSkillList getPassiveSkillList() {
        return passiveSkillList;
    }

    public void setPassiveSkillList(PassiveSkillList passiveSkillList) {
        this.passiveSkillList = passiveSkillList;
    }

    public Inventory getInventory() {
        return inventory;
    }

    public void setInventory(Inventory inventory) {
        this.inventory = inventory;
    }

    public Equipment getEquipment() {
        return equipment;
    }

    public void setEquipment(Equipment equipment) {
        this.equipment = equipment;
    }

    public BufferedImage getSprite() {
        return sprite;
    }

    public void setSprite(BufferedImage sprite) {
        this.sprite = sprite;
    }

    public boolean isVisible() {
        return isVisible;
    }

    public void setVisible(boolean visible) {
        isVisible = visible;
    }

    public Point3D getLocation() {
        return location;
    }

    public void setLocation(Point3D location) {
        this.location = location;
    }

    public Point getPixelLocation() {
        // Returns the top left pixel point of the entity's image on the screen
        return pixelLocation;
    }

    public Point getCenterPixelLocation(){
        // Returns the center pixel point of the entity's image on the screen
        int pixelX = (int)getPixelLocation().getX();
        int pixelY = (int)getPixelLocation().getY();
        pixelX += getImage().getWidth(null)/2;
        pixelY += getImage().getHeight(null)/2;
        return new Point(pixelX, pixelY);
    }

    public boolean isDead() {
        return this.getStats().isDead();
    }

    public void setPixelLocation(Point pixelLocation) {
        this.pixelLocation = pixelLocation;
    }

    public void initPixelLocation(Point pixelLocation) {
        if (this.pixelLocation == null || justMoved) {
            this.pixelLocation = pixelLocation;
        }
        if (justMoved) {
            justMoved = false;
        }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction orientation) {
        this.direction = orientation;
    }

    public Map getMap(){
        return map;
    }

    public ArrayList<Terrain> getPassableTerrains() {
        return passableTerrains;
    }

    public void setPassableTerrains(ArrayList<Terrain> passableTerrains) {
        this.passableTerrains = passableTerrains;
    }


    public Image getImage(){
        return images.get(direction);
    }

    //TODO: Will need to cover a +/- 1 in height eventually
    public Tile getTileInFront(){
        Point3D point = direction.getPointAdjacentTo(location);
        return map.getTile(point);
    }


    //TODO: Make all the behavior consequences affect the avatar (the entity)
    public void makeSleep(){

    }

    public void wakeUp(){

    }

    public void fear(Direction direction){

    }

    public void dontFear(){

    }

    public  HashMap<Direction,BufferedImage>  polymorph(){
        return null;
    }

    public void changeBack(HashMap<Direction,BufferedImage> images) {

    }

    public boolean isFlyer(){ return this.isFlyer; }

    public void setFlyer(boolean fly){
        isFlyer = fly;
    }

    public HashMap<Direction,BufferedImage> getImages(){
        return this.images;
    }

    public void setImages(HashMap<Direction,BufferedImage> images){
        this.images = images;
    }

    public void setMap(Map m){
        this.map = m;
    }

    public void setAsAvatar(){
        isAvatar = true;
    }

    public boolean isAvatar(){
        return isAvatar;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        Element entity = doc.createElement("entity");
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
        if(this.inventory != null) {
            Element inventory = doc.createElement("inventory");
            inventory.setAttribute("capacity", Integer.toString(this.inventory.getCapacity()));
            entity.appendChild(inventory);

            this.inventory.save(doc, inventory);
        }

        //save equipment
        if(this.equipment != null) {
            Element equipment = doc.createElement("equipment");
            entity.appendChild(equipment);

            this.equipment.save(doc, equipment);
        }

        //save occupation
        if(this.occupation != null) {
            Element occupation = doc.createElement("occupation");
            occupation.setAttribute("value", this.occupation.toString());
            entity.appendChild(occupation);
        }

        //save stats
        if(this.stats != null){
            Element stats = doc.createElement("stats");
            entity.appendChild(stats);
            ArrayList<EquippableItem> equippedItems = unequipAllForSave();
            this.stats.save(doc, stats);
            equipAllAfterSave(equippedItems);
        }

        //save active skills
        if(this.activeSkillList != null){
            Element activeSkillList = doc.createElement("active-skill-list");
            entity.appendChild(activeSkillList);
            this.activeSkillList.save(doc,activeSkillList);
        }

        //save passive skills
        if(this.passiveSkillList != null){
            Element passiveSkillList = doc.createElement("passive-skill-list");
            entity.appendChild(passiveSkillList);
            this.passiveSkillList.save(doc,passiveSkillList);
        }

        //save passable terrains
        if(this.passableTerrains != null){
            Element passable = doc.createElement("passable-terrains");
            entity.appendChild(passable);
            for(Terrain t : passableTerrains){
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
                occupation = new Smasher();
            else if (occupationString.equals("Summoner"))
                occupation = new Summoner();
            else
                occupation = new Sneak();
        }
        else{
            occupation = new Smasher();
        }


        //Load in stats
        NodeList statNodes = data.getElementsByTagName("stats");
        if(statNodes.getLength() != 0) {
            Node node = statNodes.item(0);
            Element statElement = (Element) node;
            stats = new Stats();
            stats.load(statElement);
        }
        else{
            stats = new Stats();
            occupation.initStats(stats);
        }


        //Load in inventory
        NodeList inventoryNodes = data.getElementsByTagName("inventory");
        if(inventoryNodes.getLength() != 0){
            Node node = inventoryNodes.item(0);
            Element inventoryElement = (Element) node;
            String capacityString = inventoryElement.getAttribute("capacity");
            int capacity = Integer.parseInt(capacityString);
            inventory = new Inventory(capacity);
            inventory.load(inventoryElement);
        }
        else {
            inventory = new Inventory(20);
        }


        //Load in active skills
        NodeList activeSkillNodes = data.getElementsByTagName("active-skill-list");
        if(activeSkillNodes.getLength() != 0){
            Node node = activeSkillNodes.item(0);
            Element activeSkillsElement = (Element) node;
            activeSkillList = occupation.initActiveSkills(stats);
            activeSkillList.load(activeSkillsElement);
        }
        else {
            activeSkillList = occupation.initActiveSkills(stats);
        }


        //Load in passive skills
        NodeList passiveSkillNodes = data.getElementsByTagName("passive-skill-list");
        if(passiveSkillNodes.getLength() != 0){
            Node node = passiveSkillNodes.item(0);
            Element passiveSkillsElement = (Element) node;
            passiveSkillList = occupation.initPassiveSkills(stats);
            passiveSkillList.load(passiveSkillsElement);
        }
        else {
            passiveSkillList = occupation.initPassiveSkills(stats);
        }


        // Load in equipment
        NodeList equipmentNodes = data.getElementsByTagName("equipment");
        if(equipmentNodes.getLength() != 0){
            Node node = equipmentNodes.item(0);
            Element equipmentElement = (Element) node;
            equipment = new Equipment(stats, inventory);

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
            equipment = new Equipment(stats, inventory);
        }


        //Load in passable terrains
        NodeList passableTerrainNode = data.getElementsByTagName("passable-terrains");
        if(passableTerrainNode.getLength() != 0){
            Node node = passableTerrainNode.item(0);


            Element terrainElement = (Element) node;
            NodeList terrainNodes = terrainElement.getElementsByTagName("terrain");
            if(terrainNodes.getLength() != 0){
                this.passableTerrains = new ArrayList<>();
                for(int i = 0; i < terrainNodes.getLength(); i++) {
                    Element terrain = (Element) terrainNodes.item(i);
                    this.passableTerrains.add(Terrain.valueOf(terrain.getAttribute("type")));
                }
            }
        }
        else {
            this.passableTerrains = null;
        }


        //Load in location
        String locationString = data.getAttribute("location");
        String[] stringArray = locationString.split(",");
        int x = Integer.parseInt(stringArray[0]);
        int y = Integer.parseInt(stringArray[1]);
        int z = Integer.parseInt(stringArray[2]);
        this.location = new Point3D(x, y, z);


        //Load in direction
        String directionString = data.getAttribute("direction");
        this.direction = Direction.valueOf(directionString);


        //Load in isAvatar
        String isAvatarString = data.getAttribute("is-avatar");
        this.isAvatar = Boolean.valueOf(isAvatarString);


        //Load in isFlyer
        String flyerString = data.getAttribute("is-flyer");
        setFlyer(Boolean.valueOf(flyerString));

        images = occupation.initImages();

    }

    public void update(){
        // TODO: Update any effects that have been placed upon this entity (Enchantment/ DamageOverTime/ etc). NOTE: We may do this a different way.
    }
}
