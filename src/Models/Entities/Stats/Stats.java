package Models.Entities.Stats;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.Arrays;
import java.util.EnumMap;

/**
 * Created by sergiopuleri on 4/7/16.
 */
public class Stats implements Savable {

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

        // Init default values for stats. Mostly 0's.
        initStats();

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
    public void modifyStat(Stat stat, int delta) {
        // Get the stat and increase it
        Integer currentValue = stats.get(stat);
        currentValue += delta;

        // Check if we exceeded this stat's level cap.
        // If we did, this function will return the capped level,
        // Else, it will return the same value we passed in
        currentValue = stat.checkLevelCap(currentValue);

        if(stat == Stat.HEALTH) {
            if (currentValue > getMaxHealth())
                currentValue = getMaxHealth();
            else if (currentValue <= 0) {
                processDeath();
                return;
            }
        }
        else if(stat == Stat.MANA) {
            if(currentValue > getMaxMana())
                currentValue = getMaxMana();
            else if(currentValue <= 0)
                currentValue = 0;
        }
        else if (currentValue < 1 && stat != Stat.SKILL_POINTS)
            currentValue = 1;

        // Set the stats new value
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

    // A public function to set a stat to any value. Not just add/subtract to it
    public void setStat(Stat stat, int value) {
        // Check if this vlaue exceeds the level cap
        // If we did, this function will return the capped level,
        // Else, it will return the same value we passed in
        value = stat.checkLevelCap(value);

        if (value < 1 ) value = 1;

        // Set the stats new value
        stats.put(stat, value);
    }

    private void initStats() {
        for(Stat stat : Stat.values()){
            stats.put(stat, 0);
        }
        // Set specific values for specific stats
        stats.put(Stat.LEVEL,1 );
        stats.put(Stat.EXP_TO_LEVEL, 50);
        stats.put(Stat.LIVES, 3);
        stats.put(Stat.RADIUS_OF_VISIBILITY, 3);
    }


    /// Derived stats getters ////

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

    private void processDeath(){
        if(stats.get(Stat.LIVES) > 0){
            stats.put(Stat.LIVES, stats.get(Stat.LIVES) - 1);
            System.out.println("You died!");
            System.out.println("You have " + stats.get(Stat.LIVES) + " lives remaining.");

            //TODO: Probably give some sort of notification of death/respawn somewhere


            stats.put(Stat.HEALTH, getMaxHealth());
        }
        else{

        }
    }


    // Made this so we can basically put in a stat type enum value and call a function
    // with a HashMap. This is used for derived stats, since a function is called
    // In order to get their value. This class uses lambda functions to implement this interface
    // http://docs.oracle.com/javase/tutorial/java/javaOO/lambdaexpressions.html
    private interface DerivedStatGetter {
        int value();
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
