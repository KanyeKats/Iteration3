package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Enchantment extends ActiveSkill {
    private int activeTime;

    public abstract void activate(Entity entity);
}
