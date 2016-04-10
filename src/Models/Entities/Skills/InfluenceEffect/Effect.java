package Models.Entities.Skills.InfluenceEffect;

import Models.Entities.Entity;
import Models.Consequences.Consequence;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import javafx.geometry.Point3D;

import java.awt.Image;

/**
 * Created by johnkaufmann on 3/30/16.
 * TODO:
 */
public abstract class Effect implements Runnable {

    //I think these need to be changed to protected so that they can be used by the subclasses - Aidan

    protected int range;
    protected Point3D location;
    protected Consequence consequence;
    protected Direction direction;
    protected Map map;

    public Effect(int range, Point3D location, Consequence consequence, Direction direction, Map map) {
        this.range = range;
        this.location = location;
        this.consequence = consequence;
        this.direction = direction;
        this.map = map;
        start();
    }

    protected void start() {
        //starts a thread that moves this effect throughout the map
    }

    @Override
    public void run() {
        long lastLoopTime = System.nanoTime();
        long lastFpsTime = 0;
        long fps = 0;
        final int TARGET_FPS = 60;
        final long OPTIMAL_TIME = 1000000000 / TARGET_FPS;

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
            traverseThroughTiles();

            try {
                Thread.sleep( (lastLoopTime-System.nanoTime() + OPTIMAL_TIME)/1000000 );
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    //increase range by one
    protected abstract void traverseThroughTiles();

    protected boolean hasEntity(Tile tile) {
        return tile.getEntity() != null;
    }

    protected void dealConsequence(Entity entity) {
        //given an entity execute that consequence
        consequence.execute(entity);
    }

    public abstract Image getImage();
}
