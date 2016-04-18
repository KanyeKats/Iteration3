package Models.Entities.NPC.AI.Decision;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Map.Direction;
import Models.Map.MapUtilities.MapUtilities;

/**
 * Created by Aidan on 4/15/2016.
 */
public class RunAwayDecision implements Decision{

    Direction direction;

    public RunAwayDecision(Direction direction) {
        this.direction = direction;
    }

    @Override
    public void executeDecision(NPC npc) {
        npc.move(direction);
    }

    @Override
    public boolean continuePursuing(VisualInfo visualInfo, NPC npc) {
        return visualInfo.getStillFeared();
    }


}
