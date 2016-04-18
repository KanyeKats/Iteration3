package Models.Entities.NPC.AI.Decision;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Map.Direction;
import Models.Map.MapUtilities.MapUtilities;

/**
 * Created by Bradley on 4/14/2016.
 */
public class FollowDecision implements Decision {
    Entity followee;

    public FollowDecision(Entity followee) {
        this.followee = followee;
    }

    @Override
    public void executeDecision(NPC npc) {
        Direction movementDirection = MapUtilities.closestDirectionBetweenAandB(npc.getLocation(), followee.getLocation());
        npc.move(movementDirection);
    }

    @Override
    public boolean continuePursuing(VisualInfo visualInfo, NPC npc) {

        return followee.isVisible();
    }
}
