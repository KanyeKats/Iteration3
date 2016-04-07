package Models.Entities.Skills;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Skill {
    protected int level;

    public void incrementLevel(){
        ++level;
    }
}
