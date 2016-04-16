package Models.Entities.NPC;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Occupation.Vehicle;
import Models.Entities.Stats.Stat;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Terrain;
import Views.Graphics.Assets;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;
import java.util.HashMap;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Mount extends NPC {

    private Entity driver;
    private int entityPrevspeed;

    public Mount(Point3D location, Map map, Terrain[] passableTerrain) {
        super(new Vehicle(), location, map, passableTerrain, Personality.MOUNT);
        setMountImages();
        this.driver = null;
    }

    @Override
    public void interacted(Entity entity) {
        mount(entity);
    }

    public void mount(Entity driver) {
        if (this.driver == null) {
            this.driver = driver;
            entityPrevspeed = driver.mountVehicle(this);
            getMap().storeOffMapEntity(driver);
        }
    }

    public void unMount(Entity driver){
        if(driver != null){
            getMap().addOffMapEntity(driver);
            this.driver = null;
        }
    }

    public Entity getDriver() {
        return driver;
    }

    public void setMountImages(){
        HashMap<Direction,BufferedImage> images = getImages();
        images.clear();
        images.put(Direction.NORTH, Assets.MOUNT_NORTH);
        images.put(Direction.NORTH_EAST, Assets.MOUNT_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.MOUNT_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.MOUNT_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.MOUNT_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.MOUNT_NORTH_WEST);
    }

    public int getEntityPrevspeed() {
        return entityPrevspeed;
    }

    public void setEntityPrevspeed(int entityPrevspeed) {
        this.entityPrevspeed = entityPrevspeed;
    }
}
