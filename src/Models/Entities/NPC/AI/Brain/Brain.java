package Models.Entities.NPC.AI.Brain;

import Models.Entities.NPC.AI.Personality;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;

/**
 * Created by Aidan on 4/7/2016.
 */
public class Brain {

    private VisualCortex visualCortex;
    private FrontalLobe frontalLobe;
    private MotorCortex motorCortex;


    public Brain(Personality personality) {
        this.visualCortex = new VisualCortex();
        this.frontalLobe = new FrontalLobe(personality);
        this.motorCortex = new MotorCortex();
    }

    public void think(NPC npc){
        VisualInfo visualInfo = visualCortex.process(npc, npc.getMap());
    }
}
