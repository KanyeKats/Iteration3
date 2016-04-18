package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Consequences.PolymorphConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.ConicalEffect;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by josh on 4/6/16.
 */
public class Polymorph extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds
    private final int BASE_MANA_REQUIRED = 5;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    //TODO: create the right type of BehaviorConsequence
    public Polymorph(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new PolymorphConsequence(activeTime);
        cooldownTime = 2000;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
        setAsset(Assets.POLYMORPH);
    }

//    @Override
//    public void activate(Entity entity){
//        if(isCooledDown){
//            if(percentChanceByLevel()) {
//                effect = new ConicalEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getDirection(),entity.getMap(), decal);
//                effect.start();
//                isCooledDown = false;
//                doTheCoolDown();
//            }
//        }
//    }
    @Override
    protected void performSkill(Entity entity) {
        effect = new ConicalEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getDirection(),entity.getMap(), asset);
        effect.start();
    }

    @Override
    public void incrementLevel(){
        ++level;
        consequence = new PolymorphConsequence(activeTime);
    }

    @Override
    public String toString(){
        return "Polymorph";
    }
}
