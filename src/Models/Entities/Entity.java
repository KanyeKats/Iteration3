package Models.Entities;

import Models.Entities.NPC.Mount;
import Models.Entities.Occupation.Occupation;
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
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Utilities.Constants;
import Models.Map.Tile;
import Views.Graphics.Assets;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Entity extends Observable {
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
    private Direction orientation;
    private Map map;
    private HashMap<Direction, BufferedImage> images;
    private boolean canMove;
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
        this.orientation = orientation;
        this.map = map;
        isVisible = true;
        initImages();
    }

    public Entity(Occupation occupation, Point3D location, Map map, Terrain... passableTerrains){
        this.occupation = occupation;
        this.location = location;
        this.stats = new Stats();
        this.inventory = new Inventory(10);
        this.equipment = new Equipment(stats, inventory);
        this.sprite = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        this.orientation = Direction.NORTH;
        this.map = map;
        this.passableTerrains = new ArrayList<>(Arrays.asList(passableTerrains));
        isVisible = true;
        occupation.initStats(this.stats);
        occupation.initSkills(activeSkillList,passiveSkillList);
        initImages();

        // Setup the movement timer.
        movementTimer = new Timer();
        canMove = true;

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
            // Move the entity
            updateOrientation(direction);
            map.moveEntity(this, direction);

            // Don't allow the entity to move
            canMove = false;

            // Allow the entity to move again by setting canMove to true
            // after movement delay time has elapsed.
            movementTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    canMove = true;
                }
            }, calculateMovementDelay());
        }

    }

    // Not a mistake, I think it will be good to have overloaded move methods
    // The default will be moving a direction, but sometimes we want to move to a desired point instantly.
    // For example, teleporting, falling off a cliff etc
    public final void move(Point3D desiredPoint) {
        // TODO: implement
    }

    //Entities arent in charge of adding items to themselves right hmmm or does tile call entity.add(item)?

    public void addItemToInventory(Item item){

    }

    public void dropItem(int positionInInventory){

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

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
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
        images.put(Direction.NORTH, Assets.PLAYER_NORTH);
        images.put(Direction.NORTH_EAST, Assets.PLAYER_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.PLAYER_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.PLAYER_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.PLAYER_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.PLAYER_NORTH_WEST);
        images.put(Direction.UP, Assets.PLAYER_NORTH);
        images.put(Direction.DOWN, Assets.PLAYER_SOUTH);

    }

    private int calculateMovementDelay() {
        // Calculate the timer delay based off of the "Movement" stat,
        // Using some funky math Sergio did to gauge how much your stat
        // modifies the visual "speediness" of movement.
        int movementTimerDelay =  Constants.MAX_MOVEMENT_DELAY_MS - (stats.getStat(Stat.MOVEMENT) * 17);

        // Guard to make sure the movement delay is not less than 5ms.
        if (movementTimerDelay < Constants.MIN_MOVEMENT_DELAY_MS) {
            movementTimerDelay = Constants.MIN_MOVEMENT_DELAY_MS;
        }

        // Return it to the timer
//        System.out.println("MOVEMENT DELAY IS: ");
//        System.out.println(movementTimerDelay + "ms");
        return movementTimerDelay;
    }

    private void updateOrientation(Direction direction){

        if(direction == Direction.DOWN || direction == Direction.UP){
            images.put(direction, images.get(orientation));
        }
        orientation = direction;
    }

    public Image getImage(){

        return isVisible ? images.get(orientation) : null;
    }

    //TODO: Will need to cover a +/- 1 in height eventually
    public Tile getTileInFront(){
        Point3D point = orientation.getPointAdjacentTo(location);
        return map.getTile(point);
    }
}
