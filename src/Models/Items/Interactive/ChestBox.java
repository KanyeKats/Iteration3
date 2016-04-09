package Models.Items.Interactive;

import Models.Entities.Entity;
import Models.Entities.Inventory;
import Models.Entities.Requirements.RequirementList;
import Models.Items.Takable.TakableItem;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by rokas on 4/9/16.
 */
public class ChestBox extends InteractiveItem {
    //holds all the TakableItems that are in the chestbox
    private ArrayList<TakableItem> chestContents;

    //Constructor takes in a RequirementList and an arraylist of TakableItems
    public ChestBox(Image image, RequirementList reqs, String name, String desc, int ID) {
        super(reqs);
        this.chestContents = null;
        this.image = image;
        this.name = name;
        this.description = desc;
        this.ID = ID;
    }

    //on touch, if the reqs are fullfilled, all the items in the chestbox are transfered to entity
    //during iteration through chestContents, if the inventory is full, only the items that were transfered
    //will be removed from the chestContents, allowing player to make space and attempt to collect items again
    @Override
    public boolean onTouch(Entity entity) {
        if (reqs.allAreFullfilled(entity)) {
            //TODO: maybe change picture to opened chest (rip mvc)
            ArrayList<TakableItem> itemsRemoved = new ArrayList<>();
            for ( TakableItem item : chestContents ) {
                if (entity.addItemToInventory(item)) {
                    itemsRemoved.add(item);
                }
            }
            chestContents.removeAll(itemsRemoved);
        }
        return false;
    }

    //always returns true since one should not be able to walk on the chest!
    @Override
    public boolean preventsMovement(Entity entity) {
        return true;
    }

    public void setChestContents(ArrayList<TakableItem> contents) { this.chestContents = contents; }
}
