package Models.Entities.Stats;

import Utilities.Constants;

/**
 * Created by sergiopuleri on 4/7/16.
 */

// Enum to represent a single type of Stat
// Descriptor is just used to show the name of the stat on a view
public enum Stat {
    // Stat types we care about
    LEVEL("Level", 100),
    LIVES("Lives Left", 5),
    STRENGTH("Strength", 100),
    AGILITY("Agility", 100),
    INTELLECT("Intellect", 100),
    HARDINESS("Hardiness", 100),
    EXPERIENCE("EXP", 1000000000),
    MOVEMENT("Movement", Constants.MAX_MOVEMENT_SPEED_LEVEL),
    HEALTH("Health", 100000),
    MAX_HEALTH("Max Health", 100000),
    MANA("Mana", 100000),
    MAX_MANA("Max Mana", 100000),
    WEAPON_MODIFIER("Weapon Damage", 100000),
    ARMOR_MODIFIER("Armor Modifier", 100000),
    RADIUS_OF_VISIBILITY("Radius of Visibility", 30),
    EXP_TO_LEVEL("EXP To Level", 1000000000),
    OFFSENSIVE_RATING("Offensive Rating", 100000),
    DEFENSIVE_RATING("Defensive Rating", 100000),
    ARMOR_RATING("Armor Rating", 100000),
    SKILL_POINTS("Skill Points", 100),
    TOTAL_WEIGHT("Weight", 100),
    BARGAIN("Bargain", 100),
    OBSERVATION("Observation", 100);


    // Properties and methods (constructor)
    private String descriptor;
    private int levelCap;

    Stat(String descriptor, int levelCap) {
        this.descriptor = descriptor;
        this.levelCap = levelCap;
    }

    public String getDescriptor() {
        return descriptor;
    }

    public int getLevelCap() {
        return levelCap;
    }

    // A list of stat types which are derived
    // Used when retrieving a stat in the Stats class to know not to try to get it from the hash map.
    public static final Stat[] DERIVED_STATS;
    static {
        DERIVED_STATS = new Stat[] {MAX_HEALTH, MAX_MANA, OFFSENSIVE_RATING, DEFENSIVE_RATING, ARMOR_RATING, EXP_TO_LEVEL};
    }

    public int checkLevelCap( int currentLevel) {
        int cap = this.getLevelCap();
        if (currentLevel > cap) {
            return cap;
        }
        return currentLevel;
    }
}
