package Models.Map;

import Models.Entities.Entity;
import Models.Entities.Skills.Consequences.Consequence;
/**
 * Created by johnkaufmann on 4/6/16.
 * TODO:
 */
public class AreaEffect {
    private Consequence consequence;
    private Decal decal;

    public AreaEffect(Consequence consequence, Decal decal) {
        this.consequence = consequence;
        this.decal = decal;
    }

    public void activate(Entity entity) {
        consequence.execute(entity);
    }
}
