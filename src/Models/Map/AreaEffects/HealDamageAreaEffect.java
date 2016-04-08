package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 * TODO:
 */
public class HealDamageAreaEffect extends AreaEffect {
    public HealDamageAreaEffect(int healRate) {
        super(new ImmediateStatConsequence().makeHealDamage(healRate), new Decal().heal);
    }
}
