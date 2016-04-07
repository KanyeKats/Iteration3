package Models.Items.Takable;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Item;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public abstract class TakableItem extends Item {
    protected StatModificationList statModificationList;

    // Called when this item is "used" on an entity
    // This can be for "using" a consumable item
    // OR for "equipping an equippable item
    // The subclasses of this class will implement however they like.
    public abstract void onUse(Entity entity);

    public StatModificationList getStatModificationList() {
        return statModificationList;
    }
}
