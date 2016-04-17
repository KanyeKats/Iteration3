package Models.Entities.NPC.AI;

import Models.Entities.Entity;
import javafx.geometry.Point3D;

import java.awt.*;
import java.util.ArrayList;

/**
 * Created by Bradley on 4/14/16.
 */
public class VisualInfo {

    private ArrayList<Entity> entities;
    private ArrayList<Point3D> itemLocations;
    private ArrayList<Point3D> areaEffectLocations;
    private boolean stillFeared;

    public VisualInfo() {
        this.entities = new ArrayList<>();
        this.itemLocations = new ArrayList<>();
        this.areaEffectLocations = new ArrayList<>();
        this.stillFeared = false;
    }

    // Functions to add stuff to the visual info
    public void addEntity(Entity entity){
        entities.add(entity);
    }
    public void addItem(Point3D point){
        itemLocations.add(point);
    }
    public void addAreaEffect(Point3D point){
        areaEffectLocations.add(point);
    }

    // Getters
    public ArrayList<Entity> getEntities() {
        return entities;
    }

    public ArrayList<Point3D> getItemLocations() {
        return itemLocations;
    }

    public ArrayList<Point3D> getAreaEffectLocations() {
        return areaEffectLocations;
    }

    public void setStillFeared(boolean stillFeared) { this.stillFeared = stillFeared; }

    public boolean getStillFeared() { return this.stillFeared; }
}
