package Models.Items.Takable.Consumable;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.TakableItem;

/**
 * Created by rokas on 4/8/16.
 */
public class ConsumableItem extends TakableItem {

    public ConsumableItem() {

        this.statModificationList = new StatModificationList()
    }

    @Override
    public void onUse(Entity entity) {
        entity.getInventory().remove(this);
        statModificationList.applyModifications(entity.getStats());
    }
}
