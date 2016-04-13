package Models.Entities.Occupation;

import Models.Entities.Skills.PassiveSkills.PassiveSkill;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.BrawlingMastery;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.OneHandedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SmasherSkills.TwoHandedWeaponMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Smasher extends Occupation {

    //constructor of smasher will contain the stats that make up a smasher

    BrawlingMastery brawlingMastery;
    OneHandedWeaponMastery oneHandedWeaponMastery;
    TwoHandedWeaponMastery twoHandedWeaponMastery;


    public Smasher(){
        super();
        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH,10),
                new StatModification(Stat.AGILITY,6),
                new StatModification(Stat.INTELLECT,0),
                new StatModification(Stat.HARDINESS,10),
                new StatModification(Stat.MOVEMENT,17),
                new StatModification(Stat.MAX_HEALTH,50),
                new StatModification(Stat.MAX_MANA,0),
                new StatModification(Stat.EXPERIENCE, 0));

        this.passiveSkillList.add(brawlingMastery);
        this.passiveSkillList.add(oneHandedWeaponMastery);
        this.passiveSkillList.add(twoHandedWeaponMastery);



    }

}
