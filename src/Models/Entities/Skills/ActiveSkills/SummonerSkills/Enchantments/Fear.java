package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;

/**
 * Created by josh on 4/6/16.
 */
public class Fear extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    //TODO: add the consequence to the linear effect, once that functionality exists
    public Fear(){
        consequence = new BehaviorConsequence();
        effect = new LinearEffect();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new BehaviorConsequence();
    }
}
