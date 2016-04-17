package Models.Items.Takable.Equippable;

import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Stats.Stats;
import Models.Items.Takable.TakableItem;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public abstract class EquippableItem extends TakableItem {

    protected StatRequirement requirementToEquip;

    @Override
    public void onUse(Entity entity) {
        // Equip the item
        entity.equip(this);
    }

    // All subtypes will equip themself to a certain slot in equipment.
    public abstract boolean equip(Equipment equipment, PassiveSkillList passiveSkillList);

    // All subtyps will unequip themselves, by removing themself from the appropiate slot
    public abstract EquippableItem unequip(Equipment equipment);

    public final boolean fufillEquipRequirement(Entity entity) {
        return requirementToEquip.isFullfilled(entity);
    }





}
