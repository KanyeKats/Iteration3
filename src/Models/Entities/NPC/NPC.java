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
import Models.Map.Terrain;
import javafx.geometry.Point3D;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/6/2016.
 */
public class NPC extends Entity {

    //needs a brain and such
    Brain brain;

    public NPC(Occupation occupation, Point3D location, Map map, Terrain[]passableTerrain, Personality personality) {
        super(occupation, location, map, passableTerrain);
        this.brain = new Brain(personality, this);
    }

    @Override
    public void update() {
        super.update();
        brain.think();
    }
}
