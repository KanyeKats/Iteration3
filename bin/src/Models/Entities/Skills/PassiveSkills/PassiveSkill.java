package Models.Entities.Skills.PassiveSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.Stats;

/**
 * Created by josh on 4/6/16.
 */
public abstract class PassiveSkill extends Skill{

    protected Stats stats;
    protected StatModification statModification;

    public abstract void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor);

    public void addToStats(){
        statModification.apply(stats);
    }

    public void removeFromStats(){
        statModification.remove(stats);
    }
}
