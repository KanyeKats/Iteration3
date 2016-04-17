package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Entities.Entity;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

/**
 * Created by josh on 4/6/16.
 */
public class Fireball extends Bane {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds
    private final BufferedImage decal = Assets.FIRE; // TODO: Dont use the bug anymore lol


    public Fireball(){
        super();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void activate(Entity entity){
        System.out.println("ACTIVATE FIREBALL");
        if(isCooledDown){
            System.out.println("isCooledDown");
            if(percentChanceByLevel()) {
                System.out.println("All systems go");
                effect = new LinearEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getDirection(), entity.getMap(), decal);
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
        return "Fire Ball";
    }
}
