package Models.Entities.Requirements;

import Models.Entities.Entity;
import Models.Entities.Inventory;
import Models.Items.Item;

/**
 * Created by Magic_Buddha on 4/6/2016.
 */
public class InventoryRequirement implements Requirement {
    //This value holds the ID of an item that is required
    //to be in possession by the Entity
    private int requiredID;

//****************************CONSTRUCTORS****************************//

    //Constructor that takes in ID
    public InventoryRequirement(int id) {
        requiredID = id;
    }

    //Constructor that takes in an item and gets it's ID
    public InventoryRequirement(Item item) {
        requiredID = item.getID();
    }

//****************************/CONSTRUCTORS****************************//

    //Checks if required item id is in inventory
    @Override
    public boolean isFullfilled(Entity entity ) {
        Inventory inv = entity.getInventory();
        if ( inv.containsItemByID( requiredID ) ) return true;
        else return false;
    }

    public void setRequiredID(int id) {
        requiredID = id;
    }
}
