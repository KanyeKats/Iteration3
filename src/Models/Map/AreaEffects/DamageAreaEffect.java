package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import Views.Graphics.Assets;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/7/16.
 */
public class DamageAreaEffect extends AreaEffect {
    private int damageAmount;

    public DamageAreaEffect(int damageAmount) {
        // Set damage amount
        this.damageAmount = damageAmount;

        // Set decal
        this.decal = Decal.damage;

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Create a Stat mod list of one damage stat mod
        StatModification damageMod = new StatModification(Stat.HEALTH, damageAmount);
        StatModificationList damageMods = new StatModificationList(damageMod);

        // Set this area effects consequence
        ImmediateStatConsequence consequence = new ImmediateStatConsequence(damageMods);

        // Execute the consequence
        consequence.execute(entity);
    }

    @Override
    public Image getImage() {
//        return Assets.DAMAGE_AREA_EFFECT;
        return null;
    }
}
