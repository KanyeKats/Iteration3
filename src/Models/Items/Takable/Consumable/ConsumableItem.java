package Models.Items.Takable.Consumable;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.TakableItem;

/**
 * Created by rokas on 4/8/16.
 */
public class ConsumableItem extends TakableItem {

    public ConsumableItem(StatModificationList statModList) {
        this.statModificationList = statModList;
    }

    @Override
    public void onUse(Entity entity) {
        entity.getInventory().removeItem(this);
        statModificationList.applyModifications(entity.getStats());
    }
}