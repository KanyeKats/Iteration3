package Models.Entities.Stats;

import java.util.Arrays;
import java.util.EnumMap;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class Stats {

    // The container that holds all of the current numerical values for stats.
    EnumMap<Stat, Integer> stats;

    // The container that holds all of the current derived stats.
    // Tbh, it doesn't hold the values. It just calls the function (via the SingleAction interface)
    // to derive and return them!
    EnumMap<Stat, DerivedStatGetter> derived;

    // Stats values will not be initialized in this constructor
    // They will be initialized in the appropriate Occupation sub class,
    // Which will apply a bunch of stat mods to init the stats
    public Stats() {
        stats = new EnumMap<Stat, Integer>(Stat.class);

        for(Stat stat : Stat.values()){
            stats.put(stat, 0);
        }

        // Construct and Populate the derived stats map
        derived = new EnumMap<Stat, DerivedStatGetter>(Stat.class);
        derived.put(Stat.MAX_HEALTH, () -> getMaxHealth());
        derived.put(Stat.MAX_MANA, () -> getMaxMana());
        derived.put(Stat.OFFSENSIVE_RATING, () -> getOffensiveRating());
        derived.put(Stat.DEFENSIVE_RATING, () -> getDefensiveRating());
        derived.put(Stat.ARMOR_MODIFIER, () -> getArmorRating());
        derived.put(Stat.EXP_TO_LEVEL, () -> getExpRequiredToLevelUp());
    }

    // A public function to modify any stat's current value
    // Primarily used by stat modification(s).
    public void setStat(Stat stat, int delta) {
        Integer currentValue = stats.get(stat);
        currentValue += delta;
        stats.put(stat, currentValue);
    }

    public Integer getStat(Stat type){
        // Check if the passed in stat is a derived stat
        boolean isDerived = Arrays.asList(Stat.DERIVED_STATS).contains(type);

        // Use function map to get the derived value, if derived
        if (isDerived) {
            return derived.get(type).value();
        } else {
            return stats.get(type);
        }
    }


    // Derived stats getters
    public int getMaxHealth() {
        // Get primary stats
        Integer level = stats.get(Stat.LEVEL);
        Integer hardiness = stats.get(Stat.HARDINESS);
        // Return computed value
        return hardiness + (10 * level);
    }
    public int getMaxMana() {
        // Get primary stats
        Integer level = stats.get(Stat.LEVEL);
        Integer intellect = stats.get(Stat.INTELLECT);
        // Return computed value
        return intellect + (10 * level);
    }
    public int getOffensiveRating() {
        // Get primary stats
        Integer weaponModifier = stats.get(Stat.WEAPON_MODIFIER);
        Integer strength = stats.get(Stat.STRENGTH);
        Integer level = stats.get(Stat.LEVEL);
        // Return computed value
        return weaponModifier + strength + level;
    }
    public int getDefensiveRating() {
        // Get primary stats
        Integer agility = stats.get(Stat.AGILITY);
        Integer level = stats.get(Stat.LEVEL);
        // Return computed value
        return agility + (10 * level);
    }
    public int getArmorRating() {
        // Get primary stats
        Integer armorModifier = stats.get(Stat.ARMOR_MODIFIER);
        Integer hardiness = stats.get(Stat.HARDINESS);
        // Return computed value
        return armorModifier + hardiness;
    }
    public int getExpRequiredToLevelUp() {
        // Get primary stats
        Integer level = stats.get(Stat.LEVEL);
        // Return computed value
        return 100 + (10* (int)Math.pow(level, 2.0));
    }


    // Made this so we can basically put in a stat type enum value and call a function
    // with a HashMap. This is used for derived stats, since a function is called
    // In order to get their value. This class uses lambda functions to implement this interface
    // http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
    private interface DerivedStatGetter {
        int value();
    }
}
