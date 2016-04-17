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
import Models.Items.Takable.Equippable.WeaponType;
import Models.Map.Direction;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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
    protected void initCompatibleWeapons() {
        this.compatibleWeapons.add(WeaponType.STAFF);
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

    @Override
    public HashMap<Direction, BufferedImage> initImages() {

        HashMap<Direction, BufferedImage> images = new HashMap<>();
        images.put(Direction.NORTH, Assets.BUG_NORTH_BLUE);
        images.put(Direction.NORTH_EAST, Assets.BUG_NORTH_EAST_BLUE);
        images.put(Direction.SOUTH_EAST, Assets.BUG_SOUTH_EAST_BLUE);
        images.put(Direction.SOUTH, Assets.BUG_SOUTH_BLUE);
        images.put(Direction.SOUTH_WEST, Assets.BUG_SOUTH_WEST_BLUE);
        images.put(Direction.NORTH_WEST, Assets.BUG_NORTH_WEST_BLUE);

        return images;
        // Used for displaying the occupation on views such as equipment
    }
    @Override
    public String toString() {
        return "Summoner";
    }

}
