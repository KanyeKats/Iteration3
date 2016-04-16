package Models.Items.Takable.Equippable.Leggings;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum LeggingFactory {
    YOGA_PANTS(5000) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(ImageLoader.loadImage("./res/items/yogaPants.png"), new StatModificationList(buff), "Yoga Pants", "Some comfy, cute yoga pants", req, 5000);
        }
    },
    BRONZE_PLATE_LEGS(5001) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(Assets.PLACEHOLDER, new StatModificationList(buff), "Bronze Platelegs", "A pair of bronze platelegs", req, 5001);
        }
    },
    IRON_PLATE_LEGS(5002) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron Platelegs", "A pair of iron platelegs", req, 5002);
        }
    },
    PETER_PAN_LEGGINGS(5003) {
        @Override
        public Legging createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Legging(ImageLoader.loadImage("./res/items/peterpanleggings.png"), new StatModificationList(buff), "Peter Pan Pants", "Cute pants worn by Peter Pan. Will protect you from magic.", req, 5003);
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
        for (LeggingFactory legging : values()) {
            if (legging.getID() == id) {
                return legging.createInstance();
            }
        }
        return null;
    }
}
