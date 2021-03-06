package Models.Map.AreaEffects;

import Models.Entities.Entity;
import Models.Consequences.ImmediateStatConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;

/**
 * Created by johnkaufmann on 4/7/16.
 *
 */
public class LevelUpAreaEffect extends AreaEffect {

    public LevelUpAreaEffect() {
        // Set decal
        this.decal = Decal.level();

        // Set visibility and removability
        this.isVisible = true;
        this.isRemovable = false;
    }

    @Override
    public void activate(Entity entity) {
        // Create a Stat mod list of one single level up stat mod
        StatModificationList LevelUpStatMod = new StatModificationList(new StatModification(Stat.LEVEL, 1), new StatModification(Stat.SKILL_POINTS, 1));

        // Set this area effects consequence to a new immediate stat consequence
        // Which increase the entity's level by one, using the stat mod we just made
        ImmediateStatConsequence consequence = new ImmediateStatConsequence(LevelUpStatMod);
        // Level up the entity!
        consequence.execute(entity);
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        Element areaEffect = doc.createElement("area-effect");
        areaEffect.setAttribute("type", "LEVEL");
        areaEffect.setAttribute("value", "0");
        parentElement.appendChild(areaEffect);

        return null;
    }

}
