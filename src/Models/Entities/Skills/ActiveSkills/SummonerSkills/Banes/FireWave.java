package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Entities.Entity;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.InfluenceEffect.ConicalEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by josh on 4/6/16.
 */
public class FireWave extends Bane {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds
    private final BufferedImage decal = Assets.BUG_NORTH; // TODO: Dont use the bug anymore lol



    public FireWave(){
        super();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                effect = new ConicalEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), decal);
                effect.start();
                isCooledDown = false;
                doTheCoolDown();
            }
        }
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
    }

    @Override
    public String toString(){
        return "Fire Wave";
    }
}
