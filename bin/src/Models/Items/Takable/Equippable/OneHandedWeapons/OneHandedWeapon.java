package Models.Items.Takable.Equippable.OneHandedWeapons;

import Models.Entities.Equipment;
import Models.Entities.Requirements.RequirementList;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.OneHandedVisitor;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.TwoHandedVisitor;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;
import Models.Items.Takable.Equippable.WeaponType;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class OneHandedWeapon extends EquippableItem {
    private WeaponType weaponType = WeaponType.ONEHANDED;
    // We will just be instantiating different "Versions" of this equipment type
    public OneHandedWeapon(Image image, StatModificationList mods, String name, String description, RequirementList requirements, int ID, int price) {
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
        equipment.unequipLeftHand();
        equipment.unequipBothHands();
        equipment.addStatModsOfEquipment(this);
        equipment.setLefthand(this);
        for(int i = 0; i < passiveSkillList.size(); i++) {
            passiveSkillList.get(i).acceptVisitor(new OneHandedVisitor());
        }
        return true;
    }

    @Override
    public EquippableItem unequip(Equipment equipment) {
        return equipment.unequipLeftHand();
    }
}