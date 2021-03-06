package Models.Items.Takable;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Item;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public abstract class TakableItem extends Item {
    protected StatModificationList statModificationList;
    protected int price;

    // Called when this item is "used" on an entity
    // This can be for "using" a consumable item
    // OR for "equipping an equippable item
    // The subclasses of this class will implement however they like.
    public abstract void onUse(Entity entity);

    public StatModificationList getStatModificationList() {
        return statModificationList;
    }

    @Override
    public boolean preventsMovement(Entity entity) {
        return false;
    }

    @Override
    public boolean onTouch(Entity entity) {
        entity.addItemToInventory(this);
        return true; // Indicate that this item should be removed from the map.
    }


    public int getPrice(){
        return price;
    }

    public void setPrice(int p){
        price = p;
    }
}
