package Models.Entities.Skills.InfluenceEffect;

import Models.Entities.Entity;
import Models.Consequences.Consequence;
import Models.Map.Map;
import Models.Map.Tile;
import Utilities.Savable.Savable;
import javafx.geometry.Point3D;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.Image;

/**
 * Created by johnkaufmann on 3/30/16.
 */
public abstract class Effect implements Runnable, Savable {
    private int range;
    private Point3D location;
    private Consequence consequence;
    private Map map;

    public Effect(int range, Point3D location, Consequence consequence, Map map) {
        this.range = range;
        this.location = location;
        this.consequence = consequence;
        this.map = map;
        start();
    }

    protected void start() {
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
        traverseThroughTiles();

        //traverse through map at a certain speed for a certain range
        for (int i = 0; i < range; i++) {
            long now = System.nanoTime();
            long updateLength = now - lastLoopTime;
            lastLoopTime = now;
            double delta = updateLength / ((double)OPTIMAL_TIME);

            lastFpsTime += updateLength;
            fps++;

            if (lastFpsTime >= 1000000000)
            {
                System.out.println("(FPS: "+fps+")");
                lastFpsTime = 0;
                fps = 0;
            }

            // check to see if an entity is there and then hit them with the attack!
//            traverseThroughTiles();

            try {
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //increase range by one
    protected abstract void traverseThroughTiles();

    protected void apply(Point3D point) {
        Entity entity = getEntity(map.getTile(point));
        System.out.println(point.getX() + "," + point.getY());
        if (hasEntity(entity)) {
            dealConsequence(entity);
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

    public abstract Image getImage();

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
