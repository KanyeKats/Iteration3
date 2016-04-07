package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 * TODO:
 */
public class InstantDeathAreaEffect extends AreaEffect {
    public InstantDeathAreaEffect() {
        super(new ImmediateStatConsequence().makeInstantDeath(), Decal.death);
    }
}
