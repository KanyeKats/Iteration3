package Models.Entities;

import Models.Items.Item;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Inventory {
    private ArrayList<Item> items;
    private int inventorySize;

    public Inventory(int inventorySize){
        items = new ArrayList<>(inventorySize);
        this.inventorySize = inventorySize;
    }

    public boolean addItem(Item item){
        if(items.size() >= inventorySize){
            return false;
        }
        items.add(item);
        return true;
    }

    public void removeItem(Item item) {
        if ( items.contains(item) ) {
            items.remove(item);
        }
    }

    public boolean isFull(){
        return ( items.size() >= inventorySize );
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
        return items.size();
    }

    public Item removeRandomItem(){
        Random rand = new Random();
        int randomItem = rand.nextInt(items.size());
        return items.remove(randomItem);
    }

}
