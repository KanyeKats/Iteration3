package Models.Entities;

import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Items.Item;
import Models.Items.Takable.Equippable.Chests.Chestplate;
import Models.Items.Takable.Equippable.Boots.Boot;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Items.Takable.Equippable.Helmets.Helmet;
import Models.Items.Takable.Equippable.Leggings.Legging;
import Models.Items.Takable.Equippable.OneHandedWeapons.OneHandedWeapon;
import Models.Items.Takable.Equippable.RangedWeapons.RangedWeapon;
import Models.Items.Takable.Equippable.Staves.Staff;
import Models.Items.Takable.Equippable.TwoHandedWeapon.TwoHandedWeapon;
import Utilities.Action;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.HashMap;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Equipment implements Savable {

    public enum Slot {
        HEAD("Head"),
        CHEST("Chest"),
        LEGS("Legs"),
        BOOTS("Boots"),
        LEFT_HAND("Left"),
        RIGHT_HAND("Right"),
        BOTH_HANDS("Both");

        private String descriptor;

        Slot(String descriptor) {
            this.descriptor = descriptor;
        }

        public String getDescriptor() {
            return descriptor;
        }
    }

    // All of the available equip slots.
    // These are the places where you can hold an equippable item
    // For example, a two handed weapon will be in the "both hands" slot 
    // // and left and right hand will be empty
    private EquippableItem head;
    private EquippableItem chest;
    private EquippableItem legs;
    private EquippableItem boots;
    private EquippableItem lefthand;
    private EquippableItem righthand; // Right hand may not be used..? maybe can hold two one handed weapons?
    private EquippableItem bothhands;

    // Handle to the entity's stats so we can apply/remove stat mods.
    private Stats stats;

    // Handle to the entity's inventory so we can remove equipment and send it there.
    private Inventory inventory;

    // Get map -> to get an item at slot via an enum identifier
    private HashMap<Slot, EquipmentGetter> slotGetMap;

    // Constructor
    public Equipment(Stats stats, Inventory inventory) {
        this.stats = stats;
        this.inventory = inventory;

        initGetMapping();
    }

    private void initGetMapping() {
        this.slotGetMap = new HashMap<>();
        slotGetMap.put(Slot.HEAD, () -> getHead());
        slotGetMap.put(Slot.CHEST, () -> getChest());
        slotGetMap.put(Slot.LEGS, () -> getLegs());
        slotGetMap.put(Slot.BOOTS, () -> getBoots());
        slotGetMap.put(Slot.LEFT_HAND, () -> getLefthand());
        slotGetMap.put(Slot.RIGHT_HAND, () -> getRighthand());
        slotGetMap.put(Slot.BOTH_HANDS, () -> getBothhands());
    }


    public EquippableItem getItemAtSlot(Slot slot) {
        return slotGetMap.get(slot).get();
    }



    // Unequip methods for each equipment slot.
    // If nothing is equipped at that slot, nothing happens
    public EquippableItem unequipHead() {
        if (this.head != null ) {
            EquippableItem old = this.head;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.head = null;
            return old;
        }
        return this.head;
    }
    public EquippableItem unequipChest() {
        if (this.chest != null ) {
            EquippableItem old = this.chest;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.chest = null;
            return old;
        }
        return this.chest;
    }
    public EquippableItem unequipLeggings() {
        if (this.legs != null ) {
            EquippableItem old = this.legs;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.legs = null;
            return old;
        }
        return this.legs;
    }
    public EquippableItem unequipBoots() {
        if (this.boots != null ) {
            EquippableItem old = this.boots;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.boots = null;
            return old;
        }
        return this.boots;
    }
    public EquippableItem unequipLeftHand() {
        if (this.lefthand != null ) {
            EquippableItem old = this.lefthand;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.lefthand = null;
            return old;
        }
        return lefthand;
    }
    // All types of items "both" hands can be set to, can be any "two handed weapon"
    public EquippableItem unequipBothHands() {
        if (this.bothhands != null ) {
            EquippableItem old = this.bothhands;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.bothhands = null;
            return old;
        }
        return bothhands;
    }

    // Might not be using a "righthand" slot
    public EquippableItem unequipRightHand() {
        if (this.righthand != null) {
            EquippableItem old = this.righthand;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.righthand = null;
            return old;
        }
        return righthand;
    }

    // GETTERS
    public EquippableItem getHead() {
        return head;
    }

    public EquippableItem getChest() {
        return chest;
    }

    public EquippableItem getLegs() {
        return legs;
    }

    public EquippableItem getBoots() {
        return boots;
    }

    public EquippableItem getLefthand() {
        return lefthand;
    }

    public EquippableItem getRighthand() {
        return righthand;
    }

    public EquippableItem getBothhands() {
        return bothhands;
    }

    // SETTERS
    public void setHead(EquippableItem head) {
        this.head = head;
    }

    public void setChest(EquippableItem chest) {
        this.chest = chest;
    }

    public void setLegs(EquippableItem legs) {
        this.legs = legs;
    }

    public void setBoots(EquippableItem boots) {
        this.boots = boots;
    }

    public void setLefthand(EquippableItem lefthand) {
        this.lefthand = lefthand;
    }

    public void setRighthand(EquippableItem righthand) {
        this.righthand = righthand;
    }

    public void setBothhands(EquippableItem bothhands) {
        this.bothhands = bothhands;
    }

    // HELPERS
    public void removeStatModsOfEquipment(EquippableItem item) {
        StatModificationList modificationList = item.getStatModificationList();
        modificationList.removeModifications(stats);
    }
    public void addStatModsOfEquipment(EquippableItem item) {
        StatModificationList modificationList = item.getStatModificationList();
        modificationList.applyModifications(stats);
    }

    // Method to check if a certain item is equipped.
    public boolean containsItemByID(int ID){
        // Check each slot
        if (head != null && head.getID() == ID) return true;
        else if (chest != null && chest.getID() == ID) return true;
        else if (legs != null && legs.getID() == ID) return true;
        else if (boots != null && boots.getID() == ID) return true;
        else if (lefthand != null && lefthand.getID() == ID) return true;
        else if (righthand != null && righthand.getID() == ID) return true;
        else if (bothhands != null &&bothhands.getID() == ID) return true;
        else return false;
    }

    @Override
    public Document save(Document doc, Element parentElement) {

        if(head != null)
            getHead().save(doc, parentElement);
        if(chest != null)
            getChest().save(doc, parentElement);
        if(legs != null)
            getLegs().save(doc, parentElement);
        if(boots != null)
            getBoots().save(doc, parentElement);
        if(lefthand != null)
            getLefthand().save(doc, parentElement);
        if(righthand != null)
            getRighthand().save(doc, parentElement);
        if(bothhands != null)
            getBothhands().save(doc, parentElement);

        return doc;
    }

    @Override
    public void load(Element data) {

    }

    private interface EquipmentGetter {
        EquippableItem get();
    }
}
