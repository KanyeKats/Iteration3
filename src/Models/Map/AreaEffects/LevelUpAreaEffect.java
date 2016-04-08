package Models.Map.AreaEffects;

import Models.Entities.Skills.Consequences.Consequence;
import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 * TODO:
 */
public class LevelUpAreaEffect extends AreaEffect {
    public LevelUpAreaEffect(int levelUpDelay) {
        super(new ImmediateStatConsequence().makeLevelUp(levelUpDelay), Decal.level);
    }
}
