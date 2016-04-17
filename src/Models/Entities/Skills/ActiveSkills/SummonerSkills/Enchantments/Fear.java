package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Consequences.FearConsequence;
import Models.Entities.Entity;
import Models.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by josh on 4/6/16.
 */
public class Fear extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds
    private final BufferedImage decal = null; // TODO: Dont use the bug anymore lol


    //TODO: create the right type of BehaviorConsequence
    public Fear(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new FearConsequence(activeTime);
        cooldownTime = 2000;
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                consequence = new FearConsequence(activeTime,entity.getDirection());
                effect = new LinearEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), decal);
                effect.start();
                isCooledDown = false;
                doTheCoolDown();
            }
        }
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
