package Models.Entities.Stats;

import Utilities.Savable.Savable;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import java.util.ArrayList;

/**
 * Created by josh on 4/6/16.
 */
public class StatModificationList implements Savable {
    // List of stat modifications
    private ArrayList<StatModification> mods = new ArrayList<>();

    // Constructor. Takes in any amount of stat modifications
    public StatModificationList(StatModification... modifications) {
        for (StatModification modification : modifications) {
            mods.add(modification);
        }
    }

    // Applys it's list of stat modifications to any entity's passed in stats.
    public void applyModifications(Stats stats) {
        for (StatModification statMod : mods) {
            statMod.apply(stats);
        }
    }

    // Removes it's list of stat modifications to any entity's passed in stats.
    public void removeModifications(Stats stats) {
        for (StatModification statMod : mods) {
            statMod.remove(stats);
        }
    }

    public void addModification(StatModification... modifications){
        for(StatModification modification: modifications){
            mods.add(modification);
        }
    }

    public ArrayList<StatModification> getMods() {
        return mods;
    }

    @Override
    public Document save(Document doc, Element parentElement) {
        return null;
    }

    @Override
    public void load(Element data) {

    }
}
