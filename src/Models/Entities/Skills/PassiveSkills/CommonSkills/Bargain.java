package Models.Entities.Skills.PassiveSkills.CommonSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.BargainVisitor;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;

/**
 * Created by josh on 4/6/16.
 */
public class Bargain extends PassiveSkill {

    public Bargain(){
        level = 0;
        statModification = new StatModification(Stat.BARGAIN, level);
        addToStats();
    }

    @Override
    public void incrementLevel(){
        ++level;
        removeFromStats();
        statModification = new StatModification(Stat.BARGAIN, level);
        addToStats();
    }

    // Blank overriden interface method added here so that the codebase compiles.
    // TODO: implement function
    @Override
    public void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor) {
        passiveSkillVisitor.activate(this);
    }
}
