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
public class GoToItemDecision implements Decision {

    Point3D itemLocation;

    public GoToItemDecision(Point3D location) {
        this.itemLocation = location;
    }

    @Override
    public void executeDecision(NPC npc) {
        Direction movementDirection = MapUtilities.closestDirectionBetweenAandB(npc.getLocation(), itemLocation);
        npc.move(movementDirection);
    }

    @Override
    public boolean continuePursuing(VisualInfo visualInfo) {

        ArrayList<Point3D> itemLocations = visualInfo.getItemLocations();
        if(itemLocations.contains(itemLocations)){
            return true;
        }
        return false;
    }
}
