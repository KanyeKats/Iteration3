package Models.Entities.NPC.AI.Decision;

import Models.Entities.Entity;
import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Map.Direction;
import Models.Map.MapUtilities.MapUtilities;
import javafx.geometry.Point3D;

import java.util.ArrayList;

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
    public boolean continuePursuing(VisualInfo visualInfo) {

//        ArrayList<Entity> entityLocations = visualInfo.getEntities();
//        if(entityLocations.contains(followee)){
//            return true;
//        }
//        return false;
        return true; // Always try to keep following.
    }
}