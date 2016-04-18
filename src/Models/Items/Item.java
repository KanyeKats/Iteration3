package Models.Items;

import Models.Entities.Entity;
import Models.Items.Interactive.InteractiveItem;
import Models.Items.Interactive.InteractiveItemFactory;
import Models.Items.Obstacle.ObstacleFactory;
import Models.Items.OneShot.OneShotItemFactory;
import Models.Items.Takable.Consumable.ConsumableItemFactory;
import Models.Items.Takable.Equippable.Boots.BootFactory;
import Models.Items.Takable.Equippable.Chests.ChestFactory;
import Models.Items.Takable.Equippable.Gauntlets.GuantletFactory;
import Models.Items.Takable.Equippable.Helmets.HelmetFactory;
import Models.Items.Takable.Equippable.Leggings.LeggingFactory;
import Models.Items.Takable.Equippable.OneHandedWeapons.OneHandedWeaponFactory;
import Models.Items.Takable.Equippable.RangedWeapons.RangedWeaponFactory;
import Models.Items.Takable.Equippable.Staves.StaffFactory;
import Models.Items.Takable.Equippable.TwoHandedWeapon.TwoHandedWeaponFactory;
import Models.Items.Takable.TakableItem;
import Models.Items.Takable.Usable.UsableItemFactory;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

/**
 * Created by Bradley on 4/5/2016.
 */
public abstract class Item implements Savable {
    //just needed to use item for its ID
    protected int ID;

    // All items should have a name and description
    protected String name;
    protected String description;

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

    @Override
    public Document save(Document doc, Element parentElement) {
        Element item = doc.createElement("item");
        item.setAttribute("id", Integer.toString(ID));
        parentElement.appendChild(item);

        return null;
    }

    public Document save(Document doc, Element parentElement, String tag) {
        Element item = doc.createElement(tag);
        item.setAttribute("id", Integer.toString(ID));
        parentElement.appendChild(item);

        return null;
    }

    @Override
    public void load(Element data) {

    }

    public static Item itemFromID(int ID) {
        // Search every factory lol

        for (InteractiveItemFactory i : InteractiveItemFactory.values()) {
            if (i.getID() == ID) {
                return InteractiveItemFactory.interactiveItemFromID(ID);
            }
        }
        for (ObstacleFactory i : ObstacleFactory.values()) {
            if (i.getID() == ID) {
                return ObstacleFactory.obstacleFromID(ID);
            }
        }
        for (OneShotItemFactory i : OneShotItemFactory.values()) {
            if (i.getID() == ID) {
                return OneShotItemFactory.oneShotItemFromID(ID);
            }
        }
        for (ConsumableItemFactory i : ConsumableItemFactory.values()) {
            if (i.getID() == ID) {
                return ConsumableItemFactory.consumableItemFromID(ID);
            }
        }
        for (UsableItemFactory i : UsableItemFactory.values()) {
            if (i.getID() == ID) {
                return UsableItemFactory.usableItemFromID(ID);
            }
        }
        for (BootFactory i : BootFactory.values()) {
            if (i.getID() == ID) {
                return BootFactory.bootsFromID(ID);
            }
        }
        for (ChestFactory i : ChestFactory.values()) {
            if (i.getID() == ID) {
                return ChestFactory.chestFromID(ID);
            }
        }
        for (GuantletFactory i : GuantletFactory.values()) {
            if (i.getID() == ID) {
                return GuantletFactory.guantletFromID(ID);
            }
        }
        for (HelmetFactory i : HelmetFactory.values()) {
            if (i.getID() == ID) {
                return HelmetFactory.helmetFromID(ID);
            }
        }
        for (LeggingFactory i : LeggingFactory.values()) {
            if (i.getID() == ID) {
                return LeggingFactory.leggingsFromID(ID);
            }
        }
        for (OneHandedWeaponFactory i : OneHandedWeaponFactory.values()) {
            if (i.getID() == ID) {
                return OneHandedWeaponFactory.oneHandedWeaponFromID(ID);
            }
        }
        for (TwoHandedWeaponFactory i : TwoHandedWeaponFactory.values()) {
            if (i.getID() == ID) {
                return TwoHandedWeaponFactory.twoHandedWeaponFromID(ID);
            }
        }
        for (StaffFactory i : StaffFactory.values()) {
            if (i.getID() == ID) {
                return StaffFactory.staffFromID(ID);
            }
        }
        for (RangedWeaponFactory i : RangedWeaponFactory.values()) {
            if (i.getID() == ID) {
                return RangedWeaponFactory.rangedWeaponFromID(ID);
            }
        }


        // Return null if passed in an invalid ID
        throw new IllegalArgumentException("Invalid Item ID");
    }
}
