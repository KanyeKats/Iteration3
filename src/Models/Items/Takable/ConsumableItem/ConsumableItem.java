package Models.Items.Takable.ConsumableItem;

import Models.Entities.Entity;
import Models.Items.Takable.TakableItem;

/**
 * Created by rokas on 4/8/16.
 */
public class ConsumableItem extends TakableItem {
    @Override
    public void onUse(Entity entity) {
        entity.getInventory().remove(this);
        entity.getOccupation().;
    }
}
