package Models.Items;

import Models.Entities.Entity;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Magic_Buddha on 4/8/2016.
 */
public class OneShotItem extends Item {
    //holds the statmods to be applied upon touch
    private StatModificationList statModificationList;

    //constructor takes in a statmodlist
    public OneShotItem(StatModificationList statModList) {
        this.statModificationList = statModList;
    }

    //on touch applies the statmods and returns true to be removed from tile
    @Override
    public boolean onTouch(Entity entity) {
        statModificationList.applyModifications(entity.getStats());
        return true;
    }

    //returns false since OneShotItem should not prevent entity from stepping on the tile
    @Override
    public boolean preventsMovement(Entity entity) {
        return false;
    }
}
