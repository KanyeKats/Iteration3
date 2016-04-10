package Models.Items.Takable.Equippable.Chests;

import Models.Entities.Equipment;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class Chestplate extends EquippableItem {
    // We will just be instantiating different "Versions" of this equipment type
    public Chestplate(Image image, StatModificationList mods, String name, String description, StatRequirement requirementToEquip) {
        this.image = image;
        this.statModificationList = mods;
        this.name = name;
        this.description = description;
        this.requirementToEquip = requirementToEquip;
    }

    @Override
    public boolean equip(Equipment equipment, PassiveSkillList passiveSkillList) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        equipment.unequipChest();
        equipment.addStatModsOfEquipment(this);
        equipment.setChest(this);
        return true;
    }

    @Override
    public EquippableItem unequip(Equipment equipment) {
        return equipment.unequipChest();
    }
}
