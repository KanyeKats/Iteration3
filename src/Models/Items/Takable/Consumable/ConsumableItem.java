package Models.Items.Takable.Consumable;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.TakableItem;

import java.awt.*;

/**
 * Created by rokas on 4/8/16.
 */
public class ConsumableItem extends TakableItem {

    //Constructor takes in a StatModificationList
    public ConsumableItem(Image image, StatModificationList statModList, String name, String desc, int ID, int price) {
        this.statModificationList = statModList;
        this.image = image;
        this.name = name;
        this.description = desc;
        this.ID = ID;
        this.price = price;
    }

    //On use, this method removes the item from inventory, and applies the statmodlist
    @Override
    public void onUse(Entity entity) {
        entity.getInventory().removeItem(this);
        statModificationList.applyModifications(entity.getStats());
    }
}
