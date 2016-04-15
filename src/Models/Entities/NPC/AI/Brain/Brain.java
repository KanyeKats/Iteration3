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
    private NPC npc;


    public Brain(Personality personality, NPC npc) {
        this.visualCortex = new VisualCortex();
        this.frontalLobe = new FrontalLobe(personality);
        this.npc = npc;
    }

    public void think(){

        VisualInfo visualInfo = visualCortex.look(npc, npc.getMap());
        frontalLobe.process(visualInfo, npc);
    }

    public boolean willTrade(){
        return frontalLobe.willTrade();
    }
    public String getDialog() { return frontalLobe.getDialog(); }
}
