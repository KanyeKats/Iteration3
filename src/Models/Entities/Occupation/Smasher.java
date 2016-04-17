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
import Models.Items.Takable.Equippable.WeaponType;
import Models.Map.Direction;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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
    protected void initCompatibleWeapons() {
        this.compatibleWeapons.add(WeaponType.BRAWLING);
        this.compatibleWeapons.add(WeaponType.ONEHANDED);
        this.compatibleWeapons.add(WeaponType.TWOHANDED);
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

    @Override
    public HashMap<Direction, BufferedImage> initImages(){

        HashMap<Direction, BufferedImage> images = new HashMap<>();
        images.put(Direction.NORTH, Assets.BUG_NORTH);
        images.put(Direction.NORTH_EAST, Assets.BUG_NORTH_EAST);
        images.put(Direction.SOUTH_EAST, Assets.BUG_SOUTH_EAST);
        images.put(Direction.SOUTH, Assets.BUG_SOUTH);
        images.put(Direction.SOUTH_WEST, Assets.BUG_SOUTH_WEST);
        images.put(Direction.NORTH_WEST, Assets.BUG_NORTH_WEST);

        return images;
    }

    // Used for displaying the occupation on views such as equipment
    @Override
    public String toString() {
        return "Smasher";
    }
}
