package Models.Entities.NPC;

import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Inventory;
import Models.Entities.Occupation.Occupation;
import Models.Map.Direction;
import com.sun.org.glassfish.external.statistics.Stats;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/6/2016.
 */
public class NPC extends Entity {

    //needs a brain and such

    public NPC(Occupation occupation, Stats stats, Inventory inventory, Equipment equipment, BufferedImage sprite, Point3D point3D, Direction orientation) {
        super(occupation, stats, inventory, equipment, sprite, point3D, orientation);
    }
}
