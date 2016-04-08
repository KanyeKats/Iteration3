package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.SneakSkills.Creep;
import Models.Entities.Skills.ActiveSkills.SneakSkills.DetectTrap;
import Models.Entities.Skills.ActiveSkills.SneakSkills.PickPocket;
import Models.Entities.Skills.ActiveSkills.SneakSkills.RemoveTrap;
import Models.Entities.Skills.PassiveSkills.SneakSkills.RangedWeaponMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Sneak extends Occupation {

    Creep creep;
    DetectTrap detectTrap;
    PickPocket pickPocket;
    RemoveTrap removeTrap;

    RangedWeaponMastery rangedWeaponMastery;

    public Sneak(){
        super();
        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH,6),
                new StatModification(Stat.AGILITY,10),
                new StatModification(Stat.INTELLECT,5),
                new StatModification(Stat.HARDINESS,6),
                new StatModification(Stat.MOVEMENT,9),
                new StatModification(Stat.MAX_HEALTH,50),
                new StatModification(Stat.MAX_MANA,50),
                new StatModification(Stat.EXPERIENCE, 0));

        this.activeSkillList.add(creep);
        this.activeSkillList.add(detectTrap);
        this.activeSkillList.add(pickPocket);
        this.activeSkillList.add(removeTrap);

        this.passiveSkillList.add(rangedWeaponMastery);

    }


}
