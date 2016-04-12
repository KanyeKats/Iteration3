package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.Effect;
import Models.Items.Item;
import Utilities.MapUtilities.TileDrawingVisitor;

import java.awt.*;
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
    private Effect effect;
    private Boolean visited;
    // TODO: User visitor pattern to construct tile image?

    public Tile(Terrain terrain, AreaEffect areaEffect, Entity entity, ArrayList<Item> items, Decal decal, Effect effect){
        this.terrain = terrain;
        this.areaEffect = areaEffect;
        this.entity = entity;
        this.items = items;
        this.decal = decal;
        this.effect = effect;
        visited = false;
    }

    public boolean containsEntity(){
        return entity!=null;
    }

    public boolean containsVisibleEntity(){
        return entity!=null && entity.isVisible();
    }

    public boolean preventsMovement(Entity entityAttemptingMovement) {

        // Check if an entity prevents movement
        if (this.containsEntity()) {
            return true;
        }

        // Check if an item prevents movement
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();) {
            Item item = iterator.next();
            if (item.preventsMovement(entityAttemptingMovement)) {
                return true;
            }
        }

        // If none of the above prevents movement, return false
        return false;
    }

    public void insertEntity(Entity entity){
        this.entity = entity;

        // Activate items
        for(Iterator<Item> iterator = items.iterator(); iterator.hasNext();){
            Item item = iterator.next();

            // Activate the item and see if it should be removed from the map after its activation.
            boolean removeItem = item.onTouch(entity);
            if(removeItem){
                iterator.remove();
            }
        }

        // Activate AreaEffects
        if(areaEffect!=null){
            areaEffect.activate(entity);
        }
    }

    public void removeEntity(){
        entity = null;
    }

    public void insertItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){
        items.remove(item);
    }

    public void insertAreaEffect(AreaEffect areaEffect){
        this.areaEffect = areaEffect;
    }

    public void removeAreaEffect(){
        this.areaEffect = null;
    }

    public void insertEffect(Effect effect){ this.effect = effect; }

    public void removeEffect() { this.effect = null; }

    public Terrain getTerrain() { return this.terrain; }

    public Image acceptDrawingVisitor(TileDrawingVisitor visitor){
        return visitor.accept(this);
    }

    public Entity getEntity() {
        return entity;
    }

    public Image getEntityImage(){
        return entity!=null ? entity.getImage() : null;
    }

    public AreaEffect getAreaEffect() { return this.areaEffect; }

    public ArrayList<Item> getItems() { return this.items; }

    public Effect getEffect() { return this.effect; }

    public Boolean wasVisited() { return this.visited; }

    public void setVisited() { this.visited = true; }
}
