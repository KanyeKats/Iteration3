package Models.Items.Obstacle;

import Models.Entities.Entity;
import Models.Items.Item;

import java.awt.*;

/**
 * Created by Magic_Buddha on 4/8/2016.
 */
public class Obstacle extends Item {
    //constructor takes in an image
    public Obstacle(Image image, String name, String desc, int ID) {
        this.image = image;
        this.name = name;
        this.description = desc;
        this.ID = ID;
    }
    @Override
    public boolean onTouch(Entity entity) {
        return false;
    }

    //An obstacle should always prevent entity from passing.
    public boolean preventsMovement(Entity entity) {
        return true;
    }
}
