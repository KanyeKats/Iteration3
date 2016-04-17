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
import Models.Map.Direction;
import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.HashMap;

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
        super.initActiveSkills(stats);
        activeSkillList.add(new Creep());
        activeSkillList.add(new DetectTrap());
        activeSkillList.add(new PickPocket());
        activeSkillList.add(new RemoveTrap());

        return activeSkillList;
    }

    @Override
    public HashMap<Direction, BufferedImage> initImages(){

        HashMap<Direction, BufferedImage> images = new HashMap<>();
        images.put(Direction.NORTH, Assets.BUG_NORTH_GREEN);
        images.put(Direction.NORTH_EAST, Assets.BUG_NORTH_EAST_GREEN);
        images.put(Direction.SOUTH_EAST, Assets.BUG_SOUTH_EAST_GREEN);
        images.put(Direction.SOUTH, Assets.BUG_SOUTH_GREEN);
        images.put(Direction.SOUTH_WEST, Assets.BUG_SOUTH_WEST_GREEN);
        images.put(Direction.NORTH_WEST, Assets.BUG_NORTH_WEST_GREEN);

        return images;
    }
    // Used for displaying the occupation on views such as equipment
    @Override
    public String toString() {
        return "Sneak";
    }


}
