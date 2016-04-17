package Models.Entities.Requirements;

import Models.Entities.Entity;

/**
 * Created by Magic_Buddha on 4/16/2016.
 */
public class OccupationRequirement implements Requirement {

    public OccupationRequirement()
    @Override
    public boolean isFullfilled(Entity entity) {

        return false;
    }
}
