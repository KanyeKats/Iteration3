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
    private Boolean isSleeping;


    public Brain(Personality personality, NPC npc) {
        this.visualCortex = new VisualCortex();
        this.frontalLobe = new FrontalLobe(personality);
        this.npc = npc;
        this.isSleeping = false;
    }

    public void think(){
        if(!isSleeping) {
            VisualInfo visualInfo = visualCortex.look(npc, npc.getMap());
            frontalLobe.process(visualInfo, npc);
        }
    }

    public void setIsSleeping(boolean isSleeping){
        this.isSleeping = isSleeping;
    }

    public boolean willTrade(){
        return frontalLobe.willTrade();
    }
    public String getDialog() { return frontalLobe.getDialog(); }
    public FrontalLobe getFrontalLobe(){ return this.frontalLobe; }
    public VisualInfo getVisualInfo(){
        return visualCortex.look(npc, npc.getMap());
    }
}
