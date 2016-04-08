package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import javafx.geometry.Point3D;

/**
 * Created by johnkaufmann on 4/6/16.
 * TODO:
 */
public class TeleportAreaEffect extends AreaEffect {
    private Point3D targetLocation;

    public TeleportAreaEffect(Point3D targetLocation) {
        // Set target location
        this.targetLocation = targetLocation;

        // Set decal
        this.decal = Decal.teleport;

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Move the entity to the target point.
        entity.move(targetLocation);
    }
}
