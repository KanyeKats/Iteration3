package Models.Entities;

import Models.Entities.NPC.Mount;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Occupation.Smasher;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.Boots.Boot;
import Models.Items.Takable.Equippable.Boots.BootFactory;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Items.Takable.Equippable.Helmets.Helmet;
import Models.Items.Takable.Equippable.Helmets.HelmetFactory;
import Models.Items.Takable.TakableItem;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Utilities.Constants;
import Models.Map.Tile;
import Utilities.Savable.Savable;
import Views.Graphics.Assets;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Entity extends Observable implements Savable {
    //TODO: make occupation
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
    private Timer movementTimer;

    // TODO: Ask about terrain checking... not sure if this is ok
    private ArrayList<Terrain> passableTerrains;


    // TODO: Whenever something changes in the entity that would change its apperance, make sure to call setChanged() notifyObservers();

    public Entity(Occupation occupation, Stats stats, Inventory inventory, Equipment equipment, BufferedImage sprite, Point3D location, Direction orientation, Map map){

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
        initImages();

    }

    public Entity(Occupation occupation, Point3D location, Map map, Terrain... passableTerrains){
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
        initImages();

        // Set movment variables
        movementTimer = new Timer();
        canMove = true;
        justMoved = false;
        enteredNewTile = false;

        // TODO: Remove!! Just testing item factory and equipping.
        Helmet bluePhat = HelmetFactory.BLUE_PHAT.createInstance();
        equip(bluePhat);
        Boot moccassins = BootFactory.bootsFromID(1001);
        equip(moccassins);

    }

    public void equip(EquippableItem item){
        // Only equip the item if this instance of entity fufills the stat requirement to equip the item.
        if (item.fufillEquipRequirement(this)) {
            item.equip(equipment, passiveSkillList);
        }
    }

    public void unequip(EquippableItem item){
        item.unequip(equipment);
    }

    public final void move(Direction direction) {
        // Move with taking movement speed in to account
        if (canMove) {
            // Don't allow the entity to move
            canMove = false;

            // Move the entity
            this.direction = direction;
            map.moveEntity(this, direction);
        }

    }
    public final void moveComplete() {
        this.justMoved = true;
        this.enteredNewTile = false;

        // Notify observers that the map changes
        setChanged();
        notifyObservers();

        // Allow movement again
        this.canMove = true;
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
        // TODO: implement
    }

    //Entities arent in charge of adding items to themselves right hmmm or does tile call entity.add(item)?
    public boolean addItemToInventory(TakableItem item){
        return inventory.addItem(item);
    }

    public void dropItem(int positionInInventory) {
        // Remove the item from the inventory
        TakableItem item = this.inventory.removeItemAtIndex(positionInInventory);

        // Drop it like its hot
        if (item != null) {
            map.insertItem(item, location);
        }
    }

    public void useSkill(Skill skill){

    }

    public void mountVehicle(Mount mount){

    }

    public void die(){

    }

    public void revive(){

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

    public void setPixelLocation(Point pixelLocation) {
        this.pixelLocation = pixelLocation;
    }

    public void initPixelLocation(Point pixelLocation) {
        if (this.pixelLocation == null || justMoved )
            this.pixelLocation = pixelLocation;
            if (justMoved) {
                justMoved = false;
            }
    }

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction orientation) {
        this.direction = direction;
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

    private void initImages(){

        images = new HashMap<>();
        images.put(Direction.NORTH, Assets.BUG_NORTH);
        images.put(Direction.NORTH_EAST, Assets.BUG_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.BUG_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.BUG_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.BUG_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.BUG_NORTH_WEST);

    }

//    public int calculateMovementDelay() {
//        // Calculate the timer delay based off of the "Movement" stat,
//        // Using some funky math Sergio did to gauge how much your stat
//        // modifies the visual "speediness" of movement.
//        int movementTimerDelay =  Constants.MAX_MOVEMENT_DELAY_MS - (stats.getStat(Stat.MOVEMENT) * 17);
//
//        // Guard to make sure the movement delay is not less than 5ms.
//        if (movementTimerDelay < Constants.MIN_MOVEMENT_DELAY_MS) {
//            movementTimerDelay = Constants.MIN_MOVEMENT_DELAY_MS;
//        }
//
//        // Return it to the timer
//        System.out.println("MOVEMENT DELAY IS: ");
//        System.out.println(movementTimerDelay + "ms");
//        return movementTimerDelay;
//    }

    public Image getImage(){

        return isVisible ? images.get(direction) : null;
    }

    //TODO: Will need to cover a +/- 1 in height eventually
    public Tile getTileInFront(){
        Point3D point = direction.getPointAdjacentTo(location);
        return map.getTile(point);
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {
        Occupation occupation = new Smasher();
        occupation.load(data);
        Stats stats = new Stats();
        stats.load(data);
        Inventory inventory = new Inventory(20);
        inventory.load(data);
        Equipment equipment = new Equipment(stats, inventory);
        equipment.load(data);
        Point3D location = new Point3D(3,3,3); // TODO: 4/14/16 have to fix this to read in the point from the xml
        Direction direction = Direction.NORTH; // TODO: 4/14/16 fix this aswell
        Map map = new Map(new HashMap<>());
        map.load(data);
        isVisible = true; // TODO: 4/14/16 read in from the file
    }
}
