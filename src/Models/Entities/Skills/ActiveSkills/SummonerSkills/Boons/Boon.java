package Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Boon extends ActiveSkill {
    private int activeTime;

    public abstract void activate(Entity entity);
}
