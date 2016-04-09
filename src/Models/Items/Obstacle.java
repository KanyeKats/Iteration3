package Models.Items;

import Models.Entities.Entity;

/**
 * Created by Magic_Buddha on 4/8/2016.
 */
public class Obstacle extends Item{
    @Override
    public boolean onTouch(Entity entity) {
        return false;
    }

    //An obstacle should always prevent entity from passing.
    public boolean preventsMovement(Entity entity) {
        return true;
    }
}
