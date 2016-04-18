package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.SphericalEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by josh on 4/6/16.
 */
public class LightningBolt extends Bane {

    private final int BASE_COOLDOWN_TIME = 1000;       //20 seconds
    private final int BASE_MANA_REQUIRED = 10;
    private final int MANA_LEVEL_MULTIPLIER = 1;


    public LightningBolt(){
        super();
        cooldownTime = BASE_COOLDOWN_TIME;
        setAsset(Assets.BEE_SOUTH);
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

//    @Override
//    public void activate(Entity entity){
//        if(isCooledDown){
//            if(percentChanceByLevel()) {
//                effect = new PrismEffect(BASE_RANGE, BASE_RANGE, entity.getLocation(), consequence, entity.getMap(), decal);
//                effect.start();
//                isCooledDown = false;
//                doTheCoolDown();
//            }
//        }
//    }

    @Override
    protected void performSkill(Entity entity) {
        effect = new SphericalEffect(BASE_RANGE, entity.getLocation(), consequence, entity.getMap(), asset);
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
        return "Lightning Bolt";
    }
}
