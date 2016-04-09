package Models.Items.Takable.Equippable.OneHandedWeapons;

import Models.Entities.Equipment;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class OneHandedWeapon extends EquippableItem {
    // We will just be instantiating different "Versions" of this equipment type
    public OneHandedWeapon(Image image, StatModificationList mods, String name, String description, StatRequirement requirement) {
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
        equipment.unequipLeftHand();
        equipment.addStatModsOfEquipment(this);
        equipment.setLefthand(this);
        return true;
    }

    @Override
    public EquippableItem unequip(Equipment equipment) {
        return equipment.unequipLeftHand();
    }
}
