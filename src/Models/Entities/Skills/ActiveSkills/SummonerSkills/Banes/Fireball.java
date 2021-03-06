package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.LinearEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by josh on 4/6/16.
 */
public class Fireball extends Bane {

    private final int BASE_COOLDOWN_TIME = 1000;       //20 seconds


    public Fireball(){
        super();
        setAsset(Assets.FIRE);
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    protected void performSkill(Entity entity) {
        effect = new LinearEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getDirection(), entity.getMap(), asset);
        effect.start();
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
