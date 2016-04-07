package Models.Items.Takable.Equippable;

import Models.Entities.Entity;
import Models.Items.Takable.TakableItem;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public abstract class EquippableItem extends TakableItem {

    @Override
    public void onUse(Entity entity) {
        // Equip the item
        // TODO: Should the entity's equip func should take care of setting statmods? I used to think think so.....
        // now i think equipment should.
        entity.equip(this);
    }




}
