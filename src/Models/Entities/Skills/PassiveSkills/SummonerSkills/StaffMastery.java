package Models.Entities.Skills.PassiveSkills.SummonerSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Stats.StatModification;

/**
 * Created by josh on 4/6/16.
 */
public class StaffMastery extends PassiveSkill{

    public void addToStats(PassiveSkillVisitor passiveSkillVisitor){
        passiveSkillVisitor.activate(this);
    }

}
