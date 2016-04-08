package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;

/**
 * Created by josh on 4/6/16.
 */
public class Fear extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds

    //TODO: create the right type of BehaviorConsequence
    public Fear(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new BehaviorConsequence(activeTime);
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                effect = new LinearEffect(BASE_RANGE, entity.getPoint3D(), consequence, entity.getMap());
                effect.run();
                isCooledDown = false;
                doTheCoolDown();
            }
        }
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new BehaviorConsequence(activeTime);
    }
}
