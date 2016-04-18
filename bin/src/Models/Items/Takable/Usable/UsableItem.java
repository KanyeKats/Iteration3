package Models.Items.Takable.Usable;

import Models.Entities.Entity;
import Models.Items.Takable.TakableItem;

import java.awt.*;

/**
 * Created by rokas on 4/9/16.
 */
public class UsableItem extends TakableItem {

    public UsableItem(Image image,String name, String desc, int ID, int price) {
        this.image = image;
        this.name = name;
        this.description = desc;
        this.ID = ID;
        this.price = price;
    }

    @Override
    public void onUse(Entity entity) {

    }
}
