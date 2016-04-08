package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.Effects.Effect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Bane extends ActiveSkill {

    protected Effect effect;
    protected final int BASE_DAMAGE_AMOUNT = 5;

    public Bane(){
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                effect.run();
            }
        }
    }

    //Calculates a random number, checks if it's more than 0.8^level
    @Override
    protected boolean percentChanceByLevel(){
        if(Math.random() > Math.pow(0.8, level))
            return true;
        else
            return false;
    }
}
