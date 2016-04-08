package Models.Entities;

import Models.Entities.NPC.Mount;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Map.Direction;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Entity {
    //TODO: make occupation
    private Occupation occupation;
    private Stats stats;
    private ActiveSkillList activeSkillList;
    private PassiveSkillList passiveSkillList;
    private Inventory inventory;
    private Equipment equipment;
    private BufferedImage sprite;
    private boolean isVisible;
    private Point3D point3D;
    private Direction orientation;

    public Entity(Occupation occupation, Stats stats, Inventory inventory, Equipment equipment, BufferedImage sprite, Point3D point3D, Direction orientation){

        this.occupation = occupation;
        this.stats = stats;
        this.inventory = inventory;
        this.equipment = equipment;
        this.sprite = sprite;
        this.point3D = point3D;
        this.orientation = orientation;
        isVisible = true;

        occupation.initStats(this.stats);
        occupation.initSkills(activeSkillList,passiveSkillList);

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

    public void addItem(Item item){

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

    public Point3D getPoint3D() {
        return point3D;
    }

    public void setPoint3D(Point3D point3D) {
        this.point3D = point3D;
    }

    public Direction getOrientation() {
        return orientation;
    }

    public void setOrientation(Direction orientation) {
        this.orientation = orientation;
    }
}
