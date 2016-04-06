package Models.Entities.Skills.PassiveSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Skills.Skill;

/**
 * Created by josh on 4/6/16.
 */
public abstract class PassiveSkill extends Skill{

    public abstract void addToStats(PassiveSkillVisitor passiveSkillVisitor);

}
