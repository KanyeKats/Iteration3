package Models.Entities.Requirements;

import Models.Entities.Entity;

/**
 * Created by Magic_Buddha on 4/6/2016.
 */
public interface Requirement {
    boolean isFullfilled(Entity entity);
}
