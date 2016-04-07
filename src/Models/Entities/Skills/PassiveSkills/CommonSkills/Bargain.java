package Models.Entities.Skills.PassiveSkills.CommonSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.BargainVisitor;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;

/**
 * Created by josh on 4/6/16.
 */
public class Bargain extends PassiveSkill {

    public void addToStats(PassiveSkillVisitor passiveSkillVisitor){
        passiveSkillVisitor.activate(this);
    }

    // Blank overriden interface method added here so that the codebase compiles.
    // TODO: implement function
    @Override
    public void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor) {

    }
}
