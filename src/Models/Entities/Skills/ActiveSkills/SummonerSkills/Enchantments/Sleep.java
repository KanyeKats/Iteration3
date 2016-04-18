package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Consequences.SleepConsequence;
import Models.Entities.Entity;
import Models.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.RadialEffect;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by josh on 4/6/16.
 */
public class Sleep extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds
    private final BufferedImage decal = Assets.SLEEP; // TODO: Dont use the bug anymore lol


    //TODO: create the right type of BehaviorConsequence
    public Sleep(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new SleepConsequence(activeTime);
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                effect = new RadialEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getMap(), decal);
                effect.start();
                isCooledDown = false;
                doTheCoolDown();
            }
        }
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
