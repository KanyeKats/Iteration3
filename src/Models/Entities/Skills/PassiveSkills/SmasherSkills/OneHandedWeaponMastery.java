package Models.Entities.Skills.PassiveSkills.SmasherSkills;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillsVisitors.PassiveSkillVisitor;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.Stats;

/**
 * Created by josh on 4/6/16.
 */
public class OneHandedWeaponMastery extends PassiveSkill {

    public OneHandedWeaponMastery(Stats stats){
        this.stats = stats;
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

    @Override
    public void acceptVisitor(PassiveSkillVisitor passiveSkillVisitor) {
        passiveSkillVisitor.activate(this);
    }

    @Override
    public String toString(){
        return "One Handed Weapon Mastery";
    }
}
