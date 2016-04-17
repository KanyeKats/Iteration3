package Models.Entities.Skills.InfluenceEffect;

import Models.Consequences.Consequence;
import Models.Entities.Entity;
import Models.Entities.Stats.Stat;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.Savable.Savable;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 3/30/16.
 */
public abstract class Effect implements Runnable, Savable {
    private int range;
    private Point3D location;
    private Consequence consequence;
    private Map map;
    private BufferedImage decal;

    public Effect(int range, Point3D location, Consequence consequence, Map map, BufferedImage decal) {
        this.range = range;
        this.location = location;
        this.consequence = consequence;
        this.map = map;
        this.decal = decal;
    }

    public void start() {
        //starts a thread that moves this effect throughout the map
        new Thread(this).start();

    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;
        long fps = 0;
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;
        ArrayList <ArrayList<Tile>> affectedTiles = getAffectedTiles();

        //traverse through map at a certain speed for a certain range
        for (int i = 0; i < affectedTiles.size(); i++) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            fps++;

            if (lastFpsTime >= 1000000000)
            {
                lastFpsTime = 0;
                fps = 0;
            }

            // check to see if an entity is there and then hit them with the attack!
            traverseThroughTiles(affectedTiles.get(i));

            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            removeDecals(affectedTiles.get(i));
        }
    }

    protected abstract ArrayList<ArrayList<Tile>> getAffectedTiles();

    //increase range by one
    protected void traverseThroughTiles(ArrayList<Tile> tiles) {
        for (Tile tile : tiles) {
            Entity entity = getEntity(tile);
            if (hasEntity(entity)) {
                dealConsequence(entity);
            }
            tile.insertEffect(this); // Insert the effect so it can be drawn.
        }
    }

    // Removes all the decals
    protected void removeDecals(ArrayList<Tile> tiles){
        for (Tile tile : tiles) {
            tile.removeEffect();
        }
    }

    protected Entity getEntity(Tile tile) {
        return tile.getEntity();
    }

    protected boolean hasEntity(Entity entity) { return entity != null; }

    //given an entity execute that consequence
    protected void dealConsequence(Entity entity) {
        consequence.execute(entity);
    }

    public Image getImage(){
        return decal;
    }

    public int getRange() {
        return range;
    }

    public Point3D getLocation() {
        return location;
    }

    public Consequence getConsequence() {
        return consequence;
    }

    public Map getMap() {
        return map;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
