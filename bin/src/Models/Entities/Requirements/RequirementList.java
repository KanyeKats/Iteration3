package Models.Entities.Requirements;

import Models.Entities.Entity;

import java.util.ArrayList;

/**
 * Created by Magic_Buddha on 4/6/2016.
 */
public class RequirementList {
    //list of Requirements
    private ArrayList<Requirement> reqs;

//****************************CONSTRUCTORS****************************//
    public RequirementList() {
        reqs = new ArrayList<>();
    }
    //you may pass an array list of Requirement if you please
    public RequirementList( ArrayList<Requirement> l ) {
        reqs = l;
    }
//****************************/CONSTRUCTORS****************************//
    public boolean allAreFullfilled( Entity entity ) {
        for ( int i = 0; i < reqs.size(); i++ ) {
            if ( reqs.get(i).isFullfilled(entity) == false ) return false;
        }
        return true;
    }

    //Add Requirement to the list of requirements
    public void addRequirement( Requirement r ) {
        reqs.add(r);
    }
}
