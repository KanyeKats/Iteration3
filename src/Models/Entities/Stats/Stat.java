package Models.Entities.Stats;

/**
 * Created by sergiopuleri on 4/7/16.
 */

// Enum to represent a single type of Stat
// Descriptor is just used to show the name of the stat on a view
public enum Stat {
    // Stat types we care about
    LEVEL("Level"),
    LIVES("Lives Left"),
    STRENGTH("Strength"),
    AGILITY("Agility"),
    INTELLECT("Intellect"),
    HARDINESS("Hardiness"),
    EXPERIENCE("EXP"),
    MOVEMENT("Movement"),
    HEALTH("Health"),
    MAX_HEALTH("Max Health"),
    MANA("Mana"),
    MAX_MANA("Max Mana"),
    WEAPON_MODIFIER("Weapon Damage"),
    ARMOR_MODIFIER("Armor Modifier"),
    RADIUS_OF_VISIBILITY("Radius of Visibility"),
    EXP_TO_LEVEL("EXP To Level"),
    OFFSENSIVE_RATING("Offensive Rating"),
    DEFENSIVE_RATING("Defensive Rating"),
    ARMOR_RATING("Armor Rating"),
    SKILL_POINTS("Skill Points"),
    TOTAL_WEIGHT("Weight");

    // Properties and methods (constructor)
    private String descriptor;

    Stat(String descriptor) {
        this.descriptor = descriptor;
    }

    public String getDescriptor() {
        return descriptor;
    }

    // A list of stat types which are derived
    // Used when retrieving a stat in the Stats class to know not to try to get it from the hash map.
    public static final Stat[] DERIVED_STATS;
    static {
        DERIVED_STATS = new Stat[] {MAX_HEALTH, MAX_MANA, OFFSENSIVE_RATING, DEFENSIVE_RATING, ARMOR_RATING, EXP_TO_LEVEL};
    }
}
