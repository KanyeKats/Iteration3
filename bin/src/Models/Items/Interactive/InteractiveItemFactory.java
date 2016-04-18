package Models.Items.Interactive;

import Models.Entities.Requirements.InventoryRequirement;
import Models.Entities.Requirements.RequirementList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by rokas on 4/9/16.
 */
public enum InteractiveItemFactory {
    CHEST_BOX(12000) {
        @Override
        public InteractiveItem createInstance() {
            InventoryRequirement invReq = new InventoryRequirement(13000);
            RequirementList reqList = new RequirementList();
            reqList.addRequirement(invReq);
            return new ChestBox(Assets.PLACEHOLDER, reqList, "Chestbox", "A chest containing items.", 12000);
        }
    },
    DOOR(12001) {
        @Override
        public InteractiveItem createInstance() {
            InventoryRequirement invReq = new InventoryRequirement(13000);
            RequirementList reqList = new RequirementList();
            reqList.addRequirement(invReq);
            return new Door(ImageLoader.loadImage("./res/items/door.png"), reqList, "Door", "A door", 12001);
        }
    };

    // Properties
    private int ID;

    // Constructor
    InteractiveItemFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract InteractiveItem createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static InteractiveItem interactiveItemFromID(int id) {
        for (InteractiveItemFactory interactiveItems : values()) {
            if (interactiveItems.getID() == id) {
                return interactiveItems.createInstance();
            }
        }
        return null;
    }
}
