package Models.Entities.Skills.ActiveSkills.CommonSkills;

import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class BasicAttack extends ActiveSkill {

    private final int BASE_COOLDOWN_TIME = 1000;       //1 second
    private final int BASE_DAMAGE_AMOUNT = 5;

    public BasicAttack(){
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
    }

    //Overriding this method because it needs a higher chance of success, I think
    //Calculates a random number, checks if it's more than 0.8^level
    @Override
    protected boolean percentChanceByLevel(){
        if(Math.random() > Math.pow(0.8, level))
            return true;
        else
            return false;
    }
}
