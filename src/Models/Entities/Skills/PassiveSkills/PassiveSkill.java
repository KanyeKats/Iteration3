package Models.Entities.Skills.PassiveSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Skills.Skill;
import Models.Entities.Stats.StatModification;
import com.sun.org.glassfish.external.statistics.Stats;

/**
 * Created by josh on 4/6/16.
 */
public abstract class PassiveSkill extends Skill{

    protected Stats stats;
    protected StatModification statModification;

    public abstract void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor);

    public void addToStats(){
        stats.applyStatMod(statModification);
    }

    public void removeFromStats(){
        stats.removeStatMod(statModification);
    }
}
