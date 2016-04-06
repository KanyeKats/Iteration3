package Models.Entities.Skills.PassiveSkills.CommonSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;

/**
 * Created by josh on 4/6/16.
 */
public class Observation extends PassiveSkill{

    public void addToStats(PassiveSkillVisitor passiveSkillVisitor){
        passiveSkillVisitor.activate(this);
    }

    //Need to actually implement this here function
    public boolean checkTileForObservableEntities(){


        return false;
    }
}
