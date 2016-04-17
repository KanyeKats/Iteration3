package Models.Items.Takable.Equippable.Helmets;

import Models.Entities.Equipment;
import Models.Entities.Requirements.RequirementList;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class Helmet extends EquippableItem {
    // We will just be instantiating different "Versions" of helmets
    public Helmet(Image image, StatModificationList mods, String name, String description, RequirementList requirements, int ID, int price) {
        this.image = image;
        this.statModificationList = mods;
        this.name = name;
        this.description = description;
        this.requirementsToEquip = requirements;
        this.ID = ID;
        this.price = price;
    }

    @Override
    public boolean equip(Equipment equipment, PassiveSkillList passiveSkillList) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        equipment.unequipHead();
        equipment.addStatModsOfEquipment(this);
        equipment.setHead(this);
        return true;
    }

    @Override
    public EquippableItem unequip(Equipment equipment) {
        return equipment.unequipHead();
    }
}
