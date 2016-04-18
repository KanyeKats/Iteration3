package Models.Entities.NPC.AI.Decision;

import Models.Entities.NPC.AI.VisualInfo;
import Models.Entities.NPC.NPC;
import Models.Map.Direction;
import Models.Map.MapUtilities.MapUtilities;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * Created by Bradley on 4/14/2016.
 */
public class GoToAreaEffectDecision implements Decision {
    Point3D areaEffectLocation;

    public GoToAreaEffectDecision(Point3D location) {
        this.areaEffectLocation = location;
    }

    @Override
    public void executeDecision(NPC npc) {
        Direction movementDirection = MapUtilities.closestDirectionBetweenAandB(npc.getLocation(), areaEffectLocation);
        npc.move(movementDirection);
    }

    @Override
    public boolean continuePursuing(VisualInfo visualInfo, NPC npc) {

        ArrayList<Point3D> areaEffectLocations = visualInfo.getAreaEffectLocations();
        if(areaEffectLocations.contains(areaEffectLocation)){
            return true;
        }
        return false;
    }
}
