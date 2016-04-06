package Models.Entities;

import Models.Entities.Occupation.Occupation;
import Models.Entities.Skills.Skill;
import Models.Items.Item;
import Models.Map.Direction;
import com.sun.org.glassfish.external.statistics.Stats;
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
    private ArrayList<Skill> SkillList = new ArrayList<>();
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

    }

    public void equip(){

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
