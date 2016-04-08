package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.RadialEffect;

/**
 * Created by josh on 4/6/16.
 */
public class Sleep extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds

    //TODO: add the consequence to the radial effect, once that functionality exists
    public Sleep(){
        consequence = new BehaviorConsequence();
        effect = new RadialEffect();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new BehaviorConsequence();
    }
}
