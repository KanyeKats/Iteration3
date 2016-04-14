package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;
import Models.Entities.Skills.ActiveSkills.SneakSkills.Creep;
import Models.Entities.Skills.ActiveSkills.SneakSkills.DetectTrap;
import Models.Entities.Skills.ActiveSkills.SneakSkills.PickPocket;
import Models.Entities.Skills.ActiveSkills.SneakSkills.RemoveTrap;
import Models.Entities.Skills.InfluenceEffect.RadialEffect;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.PassiveSkills.SneakSkills.RangedWeaponMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Sneak extends Occupation {


    public Sneak(){
        super();
        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH,6),
                new StatModification(Stat.AGILITY,10),
                new StatModification(Stat.INTELLECT,5),
                new StatModification(Stat.HARDINESS,6),
                new StatModification(Stat.MOVEMENT,18),
                new StatModification(Stat.EXPERIENCE, 0));

    }

    @Override
    public PassiveSkillList initPassiveSkills(Stats stats){
        passiveSkillList.add(new Bargain(stats));
        passiveSkillList.add(new Observation(stats));
        passiveSkillList.add(new RangedWeaponMastery(stats));
        return passiveSkillList;
    }

    @Override
    public ActiveSkillList initActiveSkills(Stats stats){
        activeSkillList.add(new BindWounds());
        activeSkillList.add(new BasicAttack());
        activeSkillList.add(new Creep());
        activeSkillList.add(new DetectTrap());
        activeSkillList.add(new PickPocket());
        activeSkillList.add(new RemoveTrap());

        return activeSkillList;
    }


}
