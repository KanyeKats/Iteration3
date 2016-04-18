package Models.Entities;

import Models.Items.Item;
import Models.Items.Takable.TakableItem;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Inventory implements Savable {
    private ArrayList<TakableItem> items;
    private int capacity;

    public Inventory(int capacity){
        items = new ArrayList<>(capacity);
        this.capacity = capacity;
    }

    public boolean addItem(TakableItem item){
        if(items.size() >= capacity){
            return false;
        }
        // Add it
        items.add(item);

        return true;
    }

    public void removeItem(TakableItem item) {
        if ( items.contains(item) ) {
            items.remove(item);
        }
    }

    public TakableItem removeItemAtIndex(int index) {
        if(index > -1 && index < size()) {

            // Remove nd return it
            return this.items.remove(index);

        }
        else return null;
    }

    public ArrayList<TakableItem> getItems() {
        return items;
    }

    public boolean isFull(){
        return ( items.size() >= capacity);
    }

    public boolean isEmpty() {
        return items.size() == 0;
    }

    public int getCapacity() {
        return capacity;
    }

    public int getCurrentSize() {
        return items.size();
    }

    public ArrayList<TakableItem> getAndRemoveAllItems() {
        ArrayList <TakableItem> items = new ArrayList<>(this.items);
        this.items.clear();

        return items;
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

    public TakableItem removeRandomItem(){
        Random rand = new Random();
        if(items.size() != 0) {
            int randomItem = rand.nextInt(items.size());
            return items.remove(randomItem);
        }
        return null;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        //save Items
        for (Item item : items) {
            item.save(doc, parentElement);
        }

        return doc;
    }

    @Override
    public void load(Element data) {
        // Get the item child nodes of the tile
        NodeList itemNodes = data.getElementsByTagName("item");

        this.items = new ArrayList<>();
        if (itemNodes.getLength() != 0) {
            // Get all item elements
            for (int i = 0; i < itemNodes.getLength(); i++) {
                // Get the node/element
                Node node = itemNodes.item(i);
                Element itemElement = (Element) node;

                // Grab the item id
                String itemID = itemElement.getAttribute("id");
                int id = Integer.parseInt(itemID);

                // Construct an instance and add it to this inventory
                TakableItem item = (TakableItem)Item.itemFromID(id);
                this.items.add(item);
            }
        }

    }
}
