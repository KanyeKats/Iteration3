package Models.Entities.Requirements;

import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Items.Item;

/**
 * Created by Magic_Buddha on 4/6/2016.
 */
public class EquipmentRequirement implements Requirement {
    //This value holds the ID of an item that is required
    //to be equipped by the Entity
    private int requiredID;

//****************************CONSTRUCTORS****************************//

    //Constructor that takes in ID
    public EquipmentRequirement(int id) {
        requiredID = id;
    }

    //Constructor that takes in an item and gets it's ID
    public EquipmentRequirement(Item item) {
        requiredID = item.getID();
    }

//****************************/CONSTRUCTORS****************************//

    //Checks if required item id is in equipment
    @Override
    public boolean isFullfilled(Entity entity ) {
        Equipment eq = entity.getEquipment();
        if ( eq.containsItemByID( requiredID ) ) return true;
        else return false;
    }

    public void setRequiredID(int id) {
        requiredID = id;
    }
}
