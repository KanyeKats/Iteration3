package Models.Items.Takable.Equippable.Helmets;

import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum HelmetFactory {
    BLUE_PHAT(3000) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 5);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Helmet(ImageLoader.loadImage("./res/items/blue_phat.png"), new StatModificationList(buff), "Blue Party Hat", "A rare blue party hat, you must be an OG player", req, 3000);
        }
    },
    IRON_HELMET(3001) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Helmet(ImageLoader.loadImage("./res/items/iron_helm.png"), new StatModificationList(buff), "Iron Helmet", "A sturdy iron helmet to protect your noggin'", req, 3001);
        }
    },
    PURPLE_PHAT(3002) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Helmet(Assets.PLACEHOLDER, new StatModificationList(buff), "Purple Party Hat", "A rare purple party hat, you gotta be OG", req, 3002);
        }
    },
    SANTA_HAT(3003) {
        @Override
        public Helmet createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            StatRequirement req = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            return new Helmet(Assets.PLACEHOLDER, new StatModificationList(buff), "Santa Hat", "A glorious santa hat which is sure to protect you from damage.", req, 3003);
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
        for (HelmetFactory helmet : values()) {
            if (helmet.getID() == id) {
                return helmet.createInstance();
            }
        }
        return null;
    }
}


