package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Skills.Effects.ConicalEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class FireWave extends Bane {

    private final int BASE_COOLDOWN_TIME = 20000;       //20 seconds


    //TODO: add the consequence to the linear effect, once that functionality exists
    public FireWave(){
        super();
        effect = new ConicalEffect();
        cooldownTime = BASE_COOLDOWN_TIME;
    }

    @Override
    public void incrementLevel(){
        ++level;
        StatModification damageStatMod = new StatModification(Stat.HEALTH, -BASE_DAMAGE_AMOUNT*level);
        consequence = new ImmediateStatConsequence(new StatModificationList(damageStatMod));
    }
}
