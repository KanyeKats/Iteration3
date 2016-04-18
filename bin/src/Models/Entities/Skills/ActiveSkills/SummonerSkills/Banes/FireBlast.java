package Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes;

import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Entity;
import Models.Entities.Skills.InfluenceEffect.AngularEffect;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by josh on 4/6/16.
 */
public class FireBlast extends Bane {

    private final int BASE_COOLDOWN_TIME = 1000;       //20 seconds

    private final int BASE_MANA_REQUIRED = 6;
    private final int MANA_LEVEL_MULTIPLIER = 1;

    public FireBlast(){
        super();
        setAsset(Assets.FIRE);
        cooldownTime = BASE_COOLDOWN_TIME;
        manaRequired = BASE_MANA_REQUIRED;
        manaLevelMultiplier = MANA_LEVEL_MULTIPLIER;
    }

    @Override
    protected void performSkill(Entity entity) {
        effect = new AngularEffect(BASE_RANGE, entity.getLocation(), consequence,entity.getDirection(), entity.getMap(), asset);
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
        return "Fire Blast";
    }
}
