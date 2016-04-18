package Models.Map.AreaEffects;

import Models.Consequences.TimedConsequence;
import Models.Consequences.TrappedConsequence;
import Models.Entities.Entity;
import Models.Consequences.BehaviorConsequence;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Map.AreaEffect;
import Models.Map.Decal;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.awt.*;
import java.util.Random;

/**
 * Created by johnkaufmann on 4/7/16.
 */
public class TrapAreaEffect extends AreaEffect {

    // Time the entity is trapped for
    private int trappedTime;

    public TrapAreaEffect(int trappedTime) {
        // Set time
        this.trappedTime = trappedTime;

        // Set decal
        this.decal = Decal.trap();

        // Set visibility and removability
        this.isVisible = false;
        this.isRemovable = true;
    }

    @Override
    public void activate(Entity entity) {
        StatModificationList statModificationList = new StatModificationList(new StatModification(Stat.MOVEMENT,-entity.getStats().getStat(Stat.MOVEMENT)));
        TimedConsequence consequence = new TimedConsequence(statModificationList, trappedTime);

        // Execute the consequence
        consequence.execute(entity);
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        Element areaEffect = doc.createElement("area-effect");
        areaEffect.setAttribute("type", "TRAP");
        areaEffect.setAttribute("value", Integer.toString(trappedTime));
        parentElement.appendChild(areaEffect);

        return null;
    }
}
