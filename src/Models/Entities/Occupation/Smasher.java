package Models.Entities.Occupation;

import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.BrawlingMastery;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.OneHandedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.TwoHandedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SneakSkills.RangedWeaponMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Smasher extends Occupation {

    //constructor of smasher will contain the stats that make up a smasher


    public Smasher(){
        super();
        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH,10),
                new StatModification(Stat.AGILITY,6),
                new StatModification(Stat.INTELLECT,0),
                new StatModification(Stat.HARDINESS,10),
                new StatModification(Stat.MOVEMENT,17),
                new StatModification(Stat.EXPERIENCE, 0));



    }

    @Override
    public PassiveSkillList initPassiveSkills(Stats stats){
        passiveSkillList.add(new Bargain(stats));
        passiveSkillList.add(new Observation(stats));
        passiveSkillList.add(new BrawlingMastery(stats));
        passiveSkillList.add(new OneHandedWeaponMastery(stats));
        passiveSkillList.add(new TwoHandedWeaponMastery(stats));
        return passiveSkillList;
    }

}
