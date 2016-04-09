package Models.Items.Takable.Equippable;

import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Requirements.StatRequirement;
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
        // TODO: Should the entity's equip func should take care of setting statmods? I used to think think so.....
        // now i think equipment should.
        entity.equip(this);
    }

    // All subtypes will equip themself to a certain slot in equipment.
    public abstract boolean equip(Equipment equipment);

    // All subtyps will unequip themselves, by removing themself from the appropiate slot
    public abstract EquippableItem unequip(Equipment equipment);

    public final boolean fufillEquipRequirement(Entity entity) {
        return requirementToEquip.isFullfilled(entity);
    }





}
