package Models.Entities;

import Models.Items.Item;
import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Inventory implements Savable {
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
                Item item = new Item() {
                    @Override
                    public boolean onTouch(Entity entity) {
                        return false;
                    }

                    @Override
                    public boolean preventsMovement(Entity entity) {
                        return false;
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
