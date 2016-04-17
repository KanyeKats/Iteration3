package Models.Items.OneShot;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by rokas on 4/9/16.
 */
public enum OneShotItemFactory {
    BUG_ZAPPER(14000) {
        @Override
        public OneShotItem createInstance() {
            StatModification buff = new StatModification(Stat.HEALTH, -1000000);
            return new OneShotItem(Assets.PLACEHOLDER, new StatModificationList(buff), "Bug Zapper", "Extremely poisonous to bugs.", 14000);
        }
    },
    CACOON(14001) {
        @Override
        public OneShotItem createInstance() {
            StatModification buff = new StatModification(Stat.DEFENSIVE_RATING, 20);
            return new OneShotItem(ImageLoader.loadImage("./res/items/cacoon.png"), new StatModificationList(buff), "Cacoon", "Increases the resilience of the bug.", 14001);
        }
    };

    // Properties
    private int ID;

    // Constructor
    OneShotItemFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract OneShotItem createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static OneShotItem oneShotItemFromID(int id) {
        for (OneShotItemFactory oneShotItem : values()) {
            if (oneShotItem.getID() == id) {
                return oneShotItem.createInstance();
            }
        }
        return null;
    }
}
