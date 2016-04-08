package Models.Entities;

import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Items.Takable.Equippable.Chests.Chestplate;
import Models.Items.Takable.Equippable.Boots.Boot;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Items.Takable.Equippable.Helmets.Helmet;
import Models.Items.Takable.Equippable.Leggings.Legging;
import Models.Items.Takable.Equippable.OneHandedWeapons.OneHandedWeapon;
import Models.Items.Takable.Equippable.RangedWeapons.RangedWeapon;
import Models.Items.Takable.Equippable.Staves.Staff;
import Models.Items.Takable.Equippable.TwoHandedWeapon.TwoHandedWeapon;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Equipment {

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

    public Equipment(Stats stats, Inventory inventory) {
        this.stats = stats;
        this.inventory = inventory;
    }

    // OVERLOADED Methods for all types of equipping,
    // This way, we just call "equip" and pass in an instance
    // of a subtype of equippable item and it will equip itself
    // in the correct equipment slot. It will also, unequip an item,
    // and remove all stat mods and add the item to the inventory
    // if an item is already equipped in the slot we are trying to equip.
    // RETURN: True if successfully equipped
    // TODO: need to utilize Conditions to equip an item? E.g. stat conditions?
    // "Must have level 10 strength to equip"
    public boolean equip(Helmet head) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.head);
        addStatModsOfEquipment(head);
        this.head = head;
        return true;
    }
    public boolean equip(Chestplate chest) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.chest);
        addStatModsOfEquipment(chest);
        this.chest = chest;
        return true;
    }
    public boolean equip(Legging legs) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.legs);
        addStatModsOfEquipment(legs);
        this.legs = legs;
        return true;
    }
    public boolean equip(Boot boots) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.boots);
        addStatModsOfEquipment(boots);
        this.boots = boots;
        return true;
    }
    public boolean equip(OneHandedWeapon oneHandedWeapon) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.lefthand);
        addStatModsOfEquipment(oneHandedWeapon);
        this.lefthand = oneHandedWeapon;
        return true;
    }
    // All types of items "both" hands can be set to, can be any "two handed weapon"
    public boolean equip(TwoHandedWeapon twoHandedWeapon) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.bothhands);
        addStatModsOfEquipment(twoHandedWeapon);
        this.bothhands = twoHandedWeapon;
        return true;
    }
    public boolean equip(RangedWeapon rangedWeapon) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.bothhands);
        addStatModsOfEquipment(rangedWeapon);
        this.bothhands = rangedWeapon;
        return true;
    }
    public boolean equip(Staff staff) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.bothhands);
        addStatModsOfEquipment(staff);
        this.bothhands = staff;
        return true;
    }
    // Might not be using a "righthand" slot
    public boolean equip(EquippableItem righthand) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        unequip(this.righthand);
        addStatModsOfEquipment(righthand);
        this.righthand = righthand;
        return true;
    }

    // OVERLOADED Methods for all types of un-equipping,
    // This way, we just call "unequip" and pass in an instance
    // of a subtype of equippable item and it will unequip the corresponding slot
    // from the correct equipment slot.
    // TOODO: Maybe return boolean if succesfull unequip?, should always be succesfull tho
    public void unequip(Helmet head) {
        if (this.head != null ) {
            EquippableItem old = this.head;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.head = null;
        }
    }
    public void unequip(Chestplate chest) {
        if (this.chest != null ) {
            EquippableItem old = this.chest;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.chest = null;
        }
    }
    public void unequip(Legging legs) {
        if (this.legs != null ) {
            EquippableItem old = this.legs;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.legs = null;
        }
    }
    public void unequip(Boot boots) {
        if (this.boots != null ) {
            EquippableItem old = this.boots;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.boots = null;
        }
    }
    public void unequip(OneHandedWeapon oneHandedWeapon) {
        if (this.lefthand != null ) {
            EquippableItem old = this.lefthand;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.lefthand = null;
        }
    }
    // All types of items "both" hands can be set to, can be any "two handed weapon"
    public void unequip(TwoHandedWeapon twoHandedWeapon) {
        if (this.bothhands != null ) {
            EquippableItem old = this.bothhands;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.bothhands = null;
        }
    }
    public void unequip(RangedWeapon rangedWeapon) {
        if (this.bothhands != null ) {
            EquippableItem old = this.bothhands;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.bothhands = null;
        }
    }
    public void unequip(Staff staff) {
        if (this.bothhands != null ) {
            EquippableItem old = this.bothhands;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.bothhands = null;
        }
    }
    // Might not be using a "righthand" slot
    public void unequip(EquippableItem righthand) {
        if (this.righthand != null) {
            EquippableItem old = this.righthand;
            removeStatModsOfEquipment(old);
            inventory.addItem(old);
            this.righthand = null;
        }
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

    // HELPERS
    private void removeStatModsOfEquipment(EquippableItem item) {
        StatModificationList modificationList = item.getStatModificationList();
        modificationList.removeModifications(stats);
    }
    private void addStatModsOfEquipment(EquippableItem item) {
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

}
