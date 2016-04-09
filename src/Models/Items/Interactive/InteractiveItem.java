package Models.Items.Interactive;

import Models.Entities.Entity;
import Models.Entities.Requirements.RequirementList;
import Models.Items.Item;

/**
 * Created by Magic_Buddha on 4/8/2016.
 */
public abstract class InteractiveItem extends Item {
    //holds requirements to be met for interaction/passability
    protected RequirementList reqs;

    public InteractiveItem(RequirementList reqs) { this.reqs = reqs; }
}
