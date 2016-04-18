package Models.Entities.Skills;

/**
 * Created by josh on 4/6/16.
 */
public abstract class Skill {
    protected int level = 1;

    public void incrementLevel(){
        ++level;
    }

    public int getLevel(){
        return level;
    }

    public void setLevel(int level){
        this.level = level;
    }
}
