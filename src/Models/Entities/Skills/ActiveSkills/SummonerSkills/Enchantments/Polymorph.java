package Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments;

import Models.Entities.Entity;
import Models.Consequences.BehaviorConsequence;
import Models.Entities.Skills.InfluenceEffect.ConicalEffect;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by josh on 4/6/16.
 */
public class Polymorph extends Enchantment {

    public final int BASE_COOLDOWN_TIME = 20000;    //20 seconds
    public final int BASE_ACTIVE_TIME = 10000;    //10 seconds
    private final BufferedImage decal = Assets.PLAYER_NORTH; // TODO: Dont use the bug anymore lol


    //TODO: create the right type of BehaviorConsequence
    public Polymorph(){
        activeTime = BASE_ACTIVE_TIME;
        consequence = new BehaviorConsequence(activeTime);
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                effect = new ConicalEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getDirection(),entity.getMap(), decal);
                effect.start();
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

    @Override
    public String toString(){
        return "Polymorph";
    }
}
