package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Consequences.FearConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;
import Views.Graphics.Assets;

/**
 * Created by josh on 4/6/16.
 */
public class Fear extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    //TODO: create the right type of BehaviorConsequence
    public Fear(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new FearConsequence(activeTime);
        cooldownTime = 2000;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
        setAsset(Assets.FEAR);
    }

//    @Override
//    public void activate(Entity entity){
//        if(isCooledDown){
//            if(percentChanceByLevel()) {
//                consequence = new FearConsequence(activeTime,entity.getDirection());
//                effect = new LinearEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), decal);
//                effect.start();
//                isCooledDown = false;
//                doTheCoolDown();
//            }
//        }
//    }

    @Override
    protected void performSkill(Entity entity) {
        consequence = new FearConsequence(activeTime,entity.getDirection());
        effect = new LinearEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), asset);
        effect.start();
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new FearConsequence(activeTime);
    }

    @Override
    public String toString(){
        return "Fear";
    }
}
