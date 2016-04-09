package Models.Entities;

import Models.Entities.NPC.Mount;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Map.Direction;
import Models.Map.Map;
import Views.Graphics.Assets;
import javafx.geometry.Point3D;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.HashMap;
import java.util.Observable;

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

    public Entity(Occupation occupation, Point3D location, Map map){
        this.occupation = occupation;
        this.location = location;
        this.stats = new Stats();
        this.inventory = new Inventory(10);
        this.equipment = new Equipment(stats, inventory);
        this.sprite = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        this.orientation = Direction.NORTH;
        this.map = map;
        isVisible = true;
        occupation.initStats(this.stats);
        occupation.initSkills(activeSkillList,passiveSkillList);
        initImages();
    }

    public void equip(EquippableItem item){
        // Boolean will be returned by the equipment equip function if the item was successfully equipped.
        boolean successfulEquip = equipment.equip(item);

        // Apply the stat mods if equipping was successful.. or do that in equipment? TDA violation?
        // WE are currently doing it in equipment because it is easier... and clearer for all cases,
        // like for unequipping.
//        if (successfulEquip) {
//            StatModificationList mods = item.getStatModificationList();
//            mods.applyModifications(stats);
//        }
    }

    public void unequip(EquippableItem item){
        equipment.unequip(item);
    }

    // TODO: Skeleton movement method
    public final void move(Direction direction) {
        // TODO: needs to take into acc movement speed.

        updateOrientation(direction);
        map.moveEntity(this, direction);
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

    private void updateOrientation(Direction direction){

        if(direction == Direction.DOWN || direction == Direction.UP){
            images.put(direction, images.get(orientation));
        }
        orientation = direction;
    }

    public Image getImage(){

        return isVisible ? images.get(orientation) : null;
    }
}
