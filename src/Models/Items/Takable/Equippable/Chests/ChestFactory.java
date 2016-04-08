package Models.Items.Takable.Equippable.Chests;

import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum ChestFactory {
    FRAT_TANK(2000) {
        @Override
        public Chestplate createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 5);
            return new Chestplate(Assets.PLACEHOLDER, new StatModificationList(buff), "Frat Tank", "A sporty tank to show off your gains");
        }
    },
    SWAMPHACKS_TEE(2001) {
        @Override
        public Chestplate createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            return new Chestplate(Assets.PLACEHOLDER, new StatModificationList(buff), "SwampHacks Tee", "A tee shirt to show off your nerdiness.");
        }
    },
    CHAINMAIL(2002) {
        @Override
        public Chestplate createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            return new Chestplate(Assets.PLACEHOLDER, new StatModificationList(buff), "Chainmail", "A sturdy chainmail to protect you from damage.");
        }
    },
    CHESTPLATE(2003) {
        @Override
        public Chestplate createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            return new Chestplate(Assets.PLACEHOLDER, new StatModificationList(buff), "Chestplate", "Stronger than chainmail, protects you from greater damage");
        }
    };

    // Properties
    private int ID;

    // Constructor
    ChestFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Chestplate createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Chestplate chestFromID(int id) {
        for (ChestFactory Chestplate : values()) {
            if (Chestplate.getID() == id) {
                return Chestplate.createInstance();
            }
        }
        return null;
    }
}


