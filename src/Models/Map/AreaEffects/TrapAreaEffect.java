package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.PeriodicStatConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 */
public class TrapAreaEffect extends AreaEffect {
    public TrapAreaEffect(int damage, int speedReduction) {
        super(new PeriodicStatConsequence().makeTrap(damage, speedReduction), Decal.trap);
    }
}
