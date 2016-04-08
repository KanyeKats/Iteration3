package Models.Items.Takable.Equippable.Helmets;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum HelmetFactory {
    BLUE_PHAT(3000) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 5);
            return new Helmet(Assets.PLACEHOLDER, new StatModificationList(buff), "Blue Party Hat", "A rare blue party hat, you must be an OG player");
        }
    },
    RED_PHAT(3001) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            return new Helmet(Assets.PLACEHOLDER, new StatModificationList(buff), "Red Party Hat", "A rare red party hat, you must be pretty old");
        }
    },
    PURPLE_PHAT(3002) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            return new Helmet(Assets.PLACEHOLDER, new StatModificationList(buff), "Purple Party Hat", "A rare purple party hat, you gotta be OG");
        }
    },
    SANTA_HAT(3003) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            return new Helmet(Assets.PLACEHOLDER, new StatModificationList(buff), "Santa Hat", "A glorious santa hat which is sure to protect you from damage.");
        }
    };

    // Properties
    private int ID;

    // Constructor
    HelmetFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Helmet createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Helmet helmetFromID(int id) {
        for (HelmetFactory Helmet : values()) {
            if (Helmet.getID() == id) {
                return Helmet.createInstance();
            }
        }
        return null;
    }
}


