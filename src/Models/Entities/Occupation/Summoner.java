package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.ActiveSkillList;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BasicAttack;
import Models.Entities.Skills.ActiveSkills.CommonSkills.BindWounds;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes.Fireball;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons.LudaSpeed;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments.Fear;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments.Sleep;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Bargain;
import Models.Entities.Skills.PassiveSkills.CommonSkills.Observation;
import Models.Entities.Skills.PassiveSkills.PassiveSkillList;
import Models.Entities.Skills.PassiveSkills.SneakSkills.RangedWeaponMastery;
import Models.Entities.Skills.PassiveSkills.SummonerSkills.StaffMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Models.Entities.Stats.Stats;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Summoner extends Occupation {

    public Summoner() {

        super();
        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH, 3),
                new StatModification(Stat.AGILITY, 5),
                new StatModification(Stat.INTELLECT, 10),
                new StatModification(Stat.HARDINESS, 4),
                new StatModification(Stat.MOVEMENT, 17),
                new StatModification(Stat.EXPERIENCE, 0));

    }

    @Override
    public PassiveSkillList initPassiveSkills(Stats stats){
        passiveSkillList.add(new Bargain(stats));
        passiveSkillList.add(new Observation(stats));
        passiveSkillList.add(new StaffMastery(stats));
        return passiveSkillList;
    }

    @Override
    public ActiveSkillList initActiveSkills(Stats stats){
        super.initActiveSkills(stats);
        activeSkillList.add(new BindWounds());
        activeSkillList.add(new BasicAttack());
        activeSkillList.add(new Fireball());
        activeSkillList.add(new LudaSpeed());
        activeSkillList.add(new Sleep());

        return activeSkillList;
    }

}
