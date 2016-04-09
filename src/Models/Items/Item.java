package Models.Items;

import Models.Entities.Entity;
import java.awt.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public abstract class Item {
    //just needed to use item for its ID
    protected int ID;

    // All items should have a name and description
    protected String name;
    protected String description;

    // TODO: all items will have an image to represent itself, right..? dunno if we have a certain asset class for that?
    // Using built in Image class for now....
    protected Image image;

    // Getters and Setters
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    // Interacts with an entity and returns a boolean if the interaction should result in its removal.
    public abstract boolean onTouch(Entity entity);

    public abstract boolean preventsMovement(Entity entity);
    public final Image getImage() {
        return image;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
}
