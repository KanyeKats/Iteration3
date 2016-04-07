package Models.Entities.Occupation;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Summoner extends Occupation {

    public Summoner() {

        this.statModificationList = new StatModificationList(
                new StatModification(Stat.STRENGTH, 3),
                new StatModification(Stat.AGILITY, 5),
                new StatModification(Stat.INTELLECT, 10),
                new StatModification(Stat.HARDINESS, 4),
                new StatModification(Stat.MOVEMENT, 7),
                new StatModification(Stat.MAX_HEALTH, 50),
                new StatModification(Stat.MAX_MANA, 50),
                new StatModification(Stat.EXPERIENCE, 0));
    }

}
