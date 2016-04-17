package Models.Entities.Skills.ActiveSkills.CommonSkills;

import Models.Entities.Entity;
import Models.Entities.Skills.ActiveSkills.ActiveSkill;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.Decal;
import Views.Graphics.Assets;

/**
 * Created by josh on 4/6/16.
 */
public class BasicAttack extends ActiveSkill {

    private final int BASE_COOLDOWN_TIME = 1000;       //1 second
    private final int BASE_DAMAGE_AMOUNT = 5;

    public BasicAttack(){
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
    }

    @Override
    public void activate(Entity entity) {
        System.out.println("ACTIVATE BASIC ATTACK");
        if(isCooledDown){
            System.out.println("isCooledDown");
            if(percentChanceByLevel()) {
                System.out.println("All systems go");
                LinearEffect effect = new LinearEffect(1, entity.getLocation(), consequence, entity.getDirection(), entity.getMap(), Assets.FIRE);
                effect.start();
                isCooledDown = false;
                doTheCoolDown();
            }
        }
    }

    //Overriding this method because it needs a higher chance of success, I think
    //Calculates a random number, checks if it's more than 0.8^level
    @Override
    protected boolean percentChanceByLevel(){
        if(Math.random() > Math.pow(0.8, level))
            return true;
        else
            return false;
    }

    @Override
    public String toString(){
        return "Basic Attack";
    }
}
