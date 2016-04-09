package Models.Items.Takable.Equippable.Gauntlets;

import Models.Entities.Equipment;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.BrawlingVisitor;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.TwoHandedVisitor;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class Guantlets extends EquippableItem {
    // We will just be instantiating different "Versions" of this equipment type
    public Guantlets(Image image, StatModificationList mods, String name, String description, StatRequirement requirement, int ID) {
        this.image = image;
        this.statModificationList = mods;
        this.name = name;
        this.description = description;
        this.requirementToEquip = requirement;
        this.ID = ID;
    }

    @Override
    public boolean equip(Equipment equipment, PassiveSkillList passiveSkillList) {
        // If pass an equipment condition do the following...

        // Remove old equipment at this slot. Always call this method.
        // If nothing is there, it wont do anything
        equipment.unequipBothHands();
        equipment.addStatModsOfEquipment(this);
        equipment.setBothhands(this);
        for(int i = 0; i < passiveSkillList.size(); i++) {
            passiveSkillList.get(i).acceptVisitor(new BrawlingVisitor());
        }
        return true;
    }

    @Override
    public EquippableItem unequip(Equipment equipment) {
        return equipment.unequipBothHands();
    }
}
