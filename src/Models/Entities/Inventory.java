package Models.Entities;

import Models.Items.Item;
import Models.Items.Takable.TakableItem;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Observable;
import java.util.Random;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Inventory extends Observable implements Savable {
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

        // Notify observers (inventory view)
        setChanged();
        notifyObservers();

        return true;
    }

    public void removeItem(TakableItem item) {
        if ( items.contains(item) ) {
            items.remove(item);
        }
        // Notify observers (inventory view)
        setChanged();
        notifyObservers();
    }

    public TakableItem removeItemAtIndex(int index) {
        if(index > -1 && index < size()) {
            // Notify observers (inventory view)
            setChanged();
            notifyObservers();

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
        int randomItem = rand.nextInt(items.size());
        return items.remove(randomItem);
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    // TODO: 4/14/16 does not work..
    @Override
    public void load(Element data) {
        try {
            // Get the tilesNodes from the xml file
            NodeList itemNodes = data.getElementsByTagName("tile");

            //find out how many tiles there are
            int numItems = itemNodes.getLength();

            //instantiate every tile to the map
            for(int i=0; i<numItems; i++) {

                Element tileElement = (Element) itemNodes.item(i);

                int x = Integer.parseInt(tileElement.getAttribute("x"));
                int y = Integer.parseInt(tileElement.getAttribute("y"));
                int z = Integer.parseInt(tileElement.getAttribute("z"));

                //construct an empty tile and load it into the game
                TakableItem item = new TakableItem() {
                    @Override
                    public void onUse(Entity entity) {
                        // idk
                    }
                };
                item.load(tileElement);


                // Check to see if this column has already been started
                this.items.add(item);
            }
        } catch (Exception e) {
            System.out.println("Error parsing map again");
            e.printStackTrace();
        }
    }
}
