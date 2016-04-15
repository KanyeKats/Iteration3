package Models.Entities.NPC;

import Models.Consequences.BehaviorConsequence;
import Models.Consequences.SleepConsequence;
import Models.Entities.Entity;
import Models.Entities.NPC.AI.Brain.Brain;
import Models.Entities.NPC.AI.Personality;
import Models.Entities.Occupation.Occupation;
import Models.Map.Map;
import Models.Map.Terrain;
import javafx.geometry.Point3D;

/**
 * Created by Aidan on 4/6/2016.
 */
public class NPC extends Entity {

    //needs a brain and such
    private Brain brain;

    public NPC(Occupation occupation, Point3D location, Map map, Terrain[]passableTerrain, Personality personality) {
        super(occupation, location, map, passableTerrain);
        this.brain = new Brain(personality, this);
    }

    @Override
    public void update() {
        super.update();
        brain.think();
    }

    public String getDialog() { return brain.getDialog(); }

    @Override
    public void makeSleep(SleepConsequence sleepConsequence) {
        super.makeSleep(sleepConsequence);



    }

    public boolean willTrade() { return brain.willTrade(); }
}
