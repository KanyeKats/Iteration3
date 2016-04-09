package Models.Items.Takable.Equippable.Leggings;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum LeggingFactory {
    YOGA_PANTS(5000) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(Assets.PLACEHOLDER, new StatModificationList(buff), "Yoga Pants", "Some comfy, cute yoga pants", req);
        }
    },
    BRONZE_PLATE_LEGS(5001) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(Assets.PLACEHOLDER, new StatModificationList(buff), "Bronze Platelegs", "A pair of bronze platelegs", req);
        }
    },
    IRON_PLATE_LEGS(5002) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron Platelegs", "A pair of iron platelegs", req);
        }
    },
    STEEL_PLATE_LEGS(5003) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(Assets.PLACEHOLDER, new StatModificationList(buff), "Steel Platelegs", "A pair of steel platelegs", req);
        }
    };

    // Properties
    private int ID;

    // Constructor
    LeggingFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Legging createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Legging leggingsFromID(int id) {
        for (LeggingFactory Legging : values()) {
            if (Legging.getID() == id) {
                return Legging.createInstance();
            }
        }
        return null;
    }
}
