package Models.Entities;

import Models.Items.Item;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Inventory {

    private Item[] items; //this can be an arraylist as well
    private int numOfItems;
    private int inventorySize;

    public Inventory(int inventorySize){
        this.items = new Item[inventorySize];
        for(Item item: items){
            item = null;
        }
        this.inventorySize = inventorySize;
        numOfItems = 0;
    }

    public void addItem(Item item){
        if(isFull()){
            return;
        }

        for(Item inventoryItem: items)
            if(inventoryItem == null){
                inventoryItem = item;
            }
        numOfItems++;
    }

    public boolean isFull(){
        return(numOfItems == inventorySize);
    }

    //The contains(ID) class Rokas wanted
    public boolean containsItemByID(int ID){
        for(Item item: items){
            if(item.getID() == ID){
                return true;
            }
        }
        return false;
    }

    public int size(){
        return numOfItems;
    }

}
