package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Consequences.SleepConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.RadialEffect;
import Views.Graphics.Assets;

/**
 * Created by josh on 4/6/16.
 */
public class Sleep extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    //TODO: create the right type of BehaviorConsequence
    public Sleep(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new SleepConsequence(activeTime);
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
        setAsset(Assets.SLEEP);
    }

//    @Override
//    public void activate(Entity entity){
//        if(isCooledDown){
//            if(percentChanceByLevel()) {
//                effect = new RadialEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getMap(), decal);
//                effect.start();
//                isCooledDown = false;
//                doTheCoolDown();
//            }
//        }
//    }

    @Override
    protected void performSkill(Entity entity) {
        effect = new RadialEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getMap(), asset);
        effect.start();
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new SleepConsequence(activeTime);
    }

    @Override
    public String toString(){
        return "Sleep";
    }
}
