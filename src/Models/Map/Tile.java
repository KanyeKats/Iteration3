package Models.Map;

import Models.Entities.Entity;
import Models.Items.Item;

import java.util.ArrayList;
import java.util.Iterator;

/**
 * Created by Bradley on 4/5/2016.
 */
public class Tile {

    private Terrain terrain;
    private AreaEffect areaEffect;
    private Decal decal;
    private Entity entity;
    private ArrayList<Item> items;
    // TODO: User visitor pattern to construct tile image?

    public Tile(Terrain terrain, AreaEffect areaEffect, Entity entity, ArrayList<Item> items, Decal decal){
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.entity = entity;
        this.items = items;
        this.decal = decal;
    }

    public boolean containsEntity(){
        return entity!=null;
    }

//    public boolean containsVisibleEntity(){
//        return entity!=null && entity.isVisible();
//    }

    public void insertEntity(Entity entity){
        this.entity = entity;

        // Activate items
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();){
            Item item = iterator.next();

            // Activate the item and see if it should be removed from the map after its activation.
//            boolean removeItem = item.onTouch(entity);
//            if(removeItem){
//                iterator.remove();
//            }
        }

        // Activate AreaEffects
        if(areaEffect!=null){
//            areaEffect.onTouch(entity);
        }
    }

    public void removeEntity(){
        entity = null;
    }

    public void insertItem(Item item){
        items.add(item);
    }

    // TODO: I Dont Think Well Need This
//    public void removeItem(Item item){
//        items.remove(item);
//    }

    public void insertAreaEffect(AreaEffect areaEffect){
        this.areaEffect = areaEffect;
    }

    public void removeAreaEffect(){
        this.areaEffect = null;
    }

}
