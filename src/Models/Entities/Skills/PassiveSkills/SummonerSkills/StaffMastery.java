package Models.Entities.Skills.PassiveSkills.SummonerSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.Stats;

/**
 * Created by josh on 4/6/16.
 */
public class StaffMastery extends PassiveSkill{

    public StaffMastery(Stats stats){
        this.stats = stats;
        level = 0;
        statModification = new StatModification(Stat.WEAPON_MODIFIER, level);
        addToStats();
    }

    @Override
    public void incrementLevel(){
        ++level;
        removeFromStats();
        statModification = new StatModification(Stat.WEAPON_MODIFIER, level);
        addToStats();
    }

    // Blank overriden interface method added here so that the codebase compiles.
    // TODO: implement function
    @Override
    public void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor) {
        passiveSkillVisitor.activate(this);
    }

}
