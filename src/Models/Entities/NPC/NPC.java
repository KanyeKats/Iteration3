package Models.Entities.NPC;

import Models.Entities.Entity;
import Models.Entities.Equipment;
import Models.Entities.Inventory;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.NPC.AI.Brain.Brain;
import Models.Entities.Occupation.Occupation;
import Models.Entities.Stats.Stats;
import Models.Map.Direction;
import Models.Map.Map;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/6/2016.
 */
public class NPC extends Entity {

    //needs a brain and such
    Brain brain;

    public NPC(Occupation occupation, Stats stats, Inventory inventory, Equipment equipment, BufferedImage sprite, Point3D point3D, Direction orientation, Map map, Personality personality) {
        super(occupation, stats, inventory, equipment, sprite, point3D, orientation, map);
        this.brain = new Brain(personality, this);
    }

    @Override
    public void update() {
        super.update();
        brain.think();
    }
}
