package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 * TODO:
 */
public class RiverAreaEffect extends AreaEffect {
    public RiverAreaEffect(int waterVelocity) {
        super(new BehaviorConsequence().makeRiver(waterVelocity), Decal.river);
    }
}
