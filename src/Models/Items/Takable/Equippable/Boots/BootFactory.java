package Models.Items.Takable.Equippable.Boots;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum BootFactory {
    MOCCASINS(1000) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Boot(Assets.PLACEHOLDER, new StatModificationList(buff), "Moccasins", "Some comfy moccasins", req);
        }
    },
    IRON(1001) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Boot(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron Boots", "Some boots made of Iron.", req);
        }
    },
    BRONZE(1002) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Boot(Assets.PLACEHOLDER, new StatModificationList(buff), "Bronze Boots", "Some boots made of Bronze.", req);
        }
    },
    STEEL(1003) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Boot(Assets.PLACEHOLDER, new StatModificationList(buff), "Steel Boots", "Some boots made of Steel.", req);
        }
    };

    // Properties
    private int ID;

    // Constructor
    BootFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Boot createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Boot bootsFromID(int id) {
        for (BootFactory boots : values()) {
            if (boots.getID() == id) {
                return boots.createInstance();
            }
        }
        return null;
    }
}
