package Models.Entities.Skills.PassiveSkills.SmasherSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Stats.StatModification;

/**
 * Created by josh on 4/6/16.
 */
public class BrawlingMastery extends PassiveSkill {

    public void addToStats(PassiveSkillVisitor passiveSkillVisitor){
        passiveSkillVisitor.activate(this);
    }

    // Blank overriden interface method added here so that the codebase compiles.
    // TODO: implement function
    @Override
    public void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor) {

    }

}
