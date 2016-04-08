package Models.Entities;

import Models.Entities.Occupation.Occupation;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Map.Direction;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Observable;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Entity extends Observable {
    //TODO: make occupation
    private Occupation occupation;
    private Stats stats;
    private ArrayList<Skill> SkillList = new ArrayList<>();
    private Inventory inventory;
    private Equipment equipment;
    private BufferedImage sprite;
    private boolean isVisible;
    private Point3D location;
    private Direction orientation;

    // TODO: Whenever something changes in the entity that would change its apperance, make sure to call setChanged() notifyObservers();

    public Entity(Occupation occupation, Stats stats, Inventory inventory, Equipment equipment, BufferedImage sprite, Point3D location, Direction orientation){

        // TODO: Make sure that instantiating an entity with existing inventorys and equipment, make sure that the Equipemnt has a correct reference to the inventory.
        this.occupation = occupation;
        this.stats = stats;
        this.inventory = inventory;
        this.equipment = equipment;
        this.sprite = sprite;
        this.location = location;
        this.orientation = orientation;
        isVisible = true;
    }

    public Entity(Occupation occupation, Point3D location){
        this.occupation = occupation;
        this.location = location;
        this.stats = new Stats();
        this.inventory = new Inventory(10);
        this.equipment = new Equipment(stats, inventory);
        this.sprite = new BufferedImage(50, 50, BufferedImage.TYPE_INT_RGB);
        this.orientation = Direction.DOWN;
        isVisible = true;
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

    public void unequip(){

    }

    //Entities arent in charge of adding items to themselves right hmmm or does tile call entity.add(item)?

    public void addItemToInventory(Item item){

    }

    public void dropItem(int positionInInventory){

    }

    public void useSkill(Skill skill){

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

    public ArrayList<Skill> getSkillList() {
        return SkillList;
    }

    public void setSkillList(ArrayList<Skill> skillList) {
        SkillList = skillList;
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
}
