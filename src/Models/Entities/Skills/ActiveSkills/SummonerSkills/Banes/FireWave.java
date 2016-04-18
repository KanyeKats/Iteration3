package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.ConicalEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by josh on 4/6/16.
 */
public class FireWave extends Bane {

    private final int BASE_COOLDOWN_TIME = 1000;       //20 seconds

    private final int BASE_MANA_REQUIRED = 7;
    private final int MANA_LEVEL_MULTIPLIER = 1;



    public FireWave(){
        super();
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

//    @Override
//    public void activate(Entity entity){
//        if(isCooledDown){
//            if(percentChanceByLevel()) {
//                effect = new ConicalEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), decal);
//                System.out.println("FireWave");
//                effect.start();
//                isCooledDown = false;
//                doTheCoolDown();
//            }
//        }
//    }

    @Override
    protected void performSkill(Entity entity) {
        effect = new ConicalEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), asset);
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
        return "Fire Wave";
    }
}
