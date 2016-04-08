package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.InfluenceEffect.AngularEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class FireBlast extends Bane {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds


    public FireBlast(){
        super();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void activate(Entity entity){
        if(isCooledDown){
            if(percentChanceByLevel()) {
                //effect = new AngularEffect();
                effect = new AngularEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getMap());
                effect.run();
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
}
