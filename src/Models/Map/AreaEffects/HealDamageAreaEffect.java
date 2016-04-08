package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;
import Models.Map.AreaEffect;
import Models.Map.Decal;

/**
 * Created by johnkaufmann on 4/7/16.
 */
public class HealDamageAreaEffect extends AreaEffect {

    private int healAmount;

    public HealDamageAreaEffect(int healAmount) {
        // Set healAmount
        this.healAmount = healAmount;

        // Set decal
        this.decal = Decal.heal;

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Create a Stat mod list of one healing stat mod
        StatModification healMod = new StatModification(Stat.HEALTH, healAmount);
        StatModificationList healStatMods = new StatModificationList(healMod);

        // Set this area effects consequence to a new immediate stat consequence
        // Which heals the entity, using the stat mod we just made
        ImmediateStatConsequence consequence = new ImmediateStatConsequence(healStatMods);

        // Execute the consequence
        consequence.execute(entity);
    }
}
