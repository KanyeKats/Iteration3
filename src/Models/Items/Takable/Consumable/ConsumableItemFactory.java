package Models.Items.Takable.Consumable;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by rokas on 4/9/16.
 */
public enum ConsumableItemFactory {
    MANA_POTION(10000) {
        @Override
        public ConsumableItem createInstance() {
            StatModification buff = new StatModification(Stat.MANA, 50);
            return new ConsumableItem(ImageLoader.loadImage("./res/items/potion.png"), new StatModificationList(buff), "Mana Potion", "Restores 50 mana.", 10000);
        }
    },
    HEALTH_POTION(10001) {
        @Override
        public ConsumableItem createInstance() {
            StatModification buff = new StatModification(Stat.HEALTH, 50);
            return new ConsumableItem(ImageLoader.loadImage("./res/items/green_potion.png"), new StatModificationList(buff), "Health Potion", "Restores 50 health.", 10001);
        }
    };

    // Properties
    private int ID;

    // Constructor
    ConsumableItemFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract ConsumableItem createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static ConsumableItem consumableItemFromID(int id) {
        for (ConsumableItemFactory consumableItems : values()) {
            if (consumableItems.getID() == id) {
                return consumableItems.createInstance();
            }
        }
        return null;
    }
}
