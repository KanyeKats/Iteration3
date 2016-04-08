package Models.Entities.Skills.Consequences;

import Models.Entities.Entity;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Consequence {
    public abstract void execute(Entity entity);

    public abstract void remove(Entity entity);
}
