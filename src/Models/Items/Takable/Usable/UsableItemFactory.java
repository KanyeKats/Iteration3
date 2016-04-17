package Models.Items.Takable.Usable;

import Views.Graphics.Assets;

/**
 * Created by rokas on 4/9/16.
 */
public enum UsableItemFactory {
    KAT_KEY(13000) {
        @Override
        public UsableItem createInstance() {
            return new UsableItem(Assets.PLACEHOLDER, "Kat Key", "Used to open a Chestbox.", 13000, 20);
        }
    };

    // Properties
    private int ID;

    // Constructor
    UsableItemFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract UsableItem createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static UsableItem usableItemFromID(int id) {
        for (UsableItemFactory usableItem : values()) {
            if (usableItem.getID() == id) {
                return usableItem.createInstance();
            }
        }
        return null;
    }
}
