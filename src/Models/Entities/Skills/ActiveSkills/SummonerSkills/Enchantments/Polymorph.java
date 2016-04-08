package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.ConicalEffect;

/**
 * Created by josh on 4/6/16.
 */
public class Polymorph extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    //TODO: add the consequence to the conical effect, once that functionality exists
    public Polymorph(){
        consequence = new BehaviorConsequence();
        effect = new ConicalEffect();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new BehaviorConsequence();
    }
}
