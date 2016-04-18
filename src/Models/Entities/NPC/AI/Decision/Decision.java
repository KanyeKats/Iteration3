package Models.Entities.NPC.AI.Decision;

import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;

/**
 * Created by Bradley on 4/14/2016.
 */
public interface Decision {

    void executeDecision(NPC npc);
    boolean continuePursuing(VisualInfo visualInfo);
}
