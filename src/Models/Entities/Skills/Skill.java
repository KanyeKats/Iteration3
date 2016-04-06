package Models.Entities.Skills;

import Models.Entities.Skills.Consequences.Consequence;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Skill {
    private int level;
    private Consequence consequence;

    public void incrementLevel(){
        ++level;
    }
}
