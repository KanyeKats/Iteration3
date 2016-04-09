package Models.Entities.Occupation;

import Models.Entities.Skills.ActiveSkills.SummonerSkills.Banes.Fireball;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Boons.LudaSpeed;
import Models.Entities.Skills.ActiveSkills.SummonerSkills.Enchantments.Fear;
import Models.Entities.Skills.PassiveSkills.SummonerSkills.StaffMastery;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Summoner extends Occupation {

    Fireball fireball;
    LudaSpeed ludaSpeed;
    Fear fear;

    StaffMastery staffMastery;

    public Summoner() {

        super();
        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH, 3),
                new StatModification(Stat.AGILITY, 5),
                new StatModification(Stat.INTELLECT, 10),
                new StatModification(Stat.HARDINESS, 4),
                new StatModification(Stat.MOVEMENT, 17),
                new StatModification(Stat.MAX_HEALTH, 50),
                new StatModification(Stat.MAX_MANA, 50),
                new StatModification(Stat.EXPERIENCE, 0));

        this.activeSkillList.add(fireball);
        this.activeSkillList.add(ludaSpeed);
        this.activeSkillList.add(fear);

        this.passiveSkillList.add(staffMastery);

    }

}
