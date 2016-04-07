package Models.Items.Takable.Equippable.Boots;

import Models.Entities.Stats.StatModificationList;
import Models.Items.Takable.Equippable.EquippableItem;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class Boot extends EquippableItem{
    // Constructor is all we rly need tbh
    // We will just be instantiating different "Versions" of this equipment type
    public Boot(Image image, StatModificationList mods, String name, String description ) {
        this.image = image;
        this.statModificationList = mods;
        this.name = name;
        this.description = description;
    }
}
