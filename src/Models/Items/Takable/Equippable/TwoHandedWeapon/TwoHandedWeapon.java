package Models.Items.Takable.Equippable.TwoHandedWeapon;

import Models.Entities.Equipment;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class TwoHandedWeapon extends EquippableItem {
    // We will just be instantiating different "Versions" of this equipment type
    public TwoHandedWeapon(Image image, StatModificationList mods, String name, String description, StatRequirement requirement) {
        this.image = image;
        this.statModificationList = mods;
        this.name = name;
        this.description = description;
        this.requirementToEquip = requirement;
    }

    @Override
    public boolean equip(Equipment equipment) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        equipment.unequipBothHands();
        equipment.addStatModsOfEquipment(this);
        equipment.setBothhands(this);
        return true;
    }

    @Override
    public EquippableItem unequip(Equipment equipment) {
        return equipment.unequipBothHands();
    }
}
