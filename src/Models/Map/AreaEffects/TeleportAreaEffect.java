package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import javafx.geometry.Point3D;

/**
 * Created by johnkaufmann on 4/6/16.
 * TODO:
 */
public class TeleportAreaEffect extends AreaEffect {
    public TeleportAreaEffect(Point3D endingPoint) {
        super(new BehaviorConsequence().makeTeleport(endingPoint), Decal.teleport); // TODO: 4/6/16 figure out decal class and use immediate stat consequence
    }
}
