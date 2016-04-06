package Models.Entities.Requirements;

import Models.Entities.Entity;
import Models.Items.Item;

/**
 * Created by Magic_Buddha on 4/6/2016.
 */
public class StatRequirement implements Requirement {
    //s holds the stat to be checked
    private Stat s;
    //reqValue holds the minimum value of the stat required
    private int reqValue;

    //Constructor that takes in Stat and a required value for it
    public StatRequirement( Stat s, int value ) {
        this.s = s;
        this.reqValue = value;
    }

    //Checks if required item id is in equipment
    @Override
    public boolean isFullfilled( Entity entity ) {
        if (entity.getStats().getStat(s) >= reqValue) return true;
        else return false;
    }

    //You may set the stat required
    public void setStat( Stat s ) {
        this.s = s;
    }

    //you may set the required value of the current stat
    public void setReqValue( int r ) {
        this.reqValue = r;
    }
}
