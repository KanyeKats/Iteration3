package Models.Items.Interactive;

import Models.Entities.Entity;
import Models.Entities.Requirements.RequirementList;

import java.awt.*;

/**
 * Created by rokas on 4/9/16.
 */
public class Door extends InteractiveItem {

    //constructor takes in a requirement list
    public Door(Image image, RequirementList reqs, String name, String desc, int ID) {
        super(reqs);
        this.image = image;
        this.name = name;
        this.description = desc;
        this.ID = ID;
    }

    //this method could change the picture of the door when entity is allowed to pass
    @Override
    public boolean onTouch(Entity entity) {
        //TODO: maybe switch the image to an opened door if all reqs fullfilled? (rip mvc)
        if (reqs.allAreFullfilled(entity)) {
            //change to opened door
        } else {
            //change to closed door
        }
        return false;
    }

    //
    @Override
    public boolean preventsMovement(Entity entity) {
        if (reqs.allAreFullfilled(entity)) return false;
        else return true;
    }
}
