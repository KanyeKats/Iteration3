package Models.Map;

import Models.Entities.Entity;
import Models.Items.Item;

import java.util.ArrayList;

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

    public Entity getEntity() {
        return entity;
    }
}
