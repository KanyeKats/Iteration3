package Models.Items.Takable.Equippable.Boots;

import Models.Entities.Requirements.RequirementList;
import Models.Entities.Requirements.StatRequirement;
import Models.Entities.Stats.Stat;
import Models.Entities.Stats.StatModification;
import Models.Entities.Stats.StatModificationList;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/8/16.
 */
public enum BootFactory {
    MOCCASINS(1000) {
        @Override
        public Boot createInstance() {
            StatModificationList buffs = new StatModificationList(new StatModification(Stat.ARMOR_MODIFIER, 5),
                                                                new StatModification(Stat.HARDINESS, 5),
                                                                new StatModification(Stat.MAX_HEALTH, 20));

            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            return new Boot(ImageLoader.loadImage("./res/items/moccassin.png"), buffs, "Moccasins", "Some comfy moccasins", reqs, 1000, 15);
        }
    },
    IRON(1001) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 10);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            return new Boot(Assets.PLACEHOLDER, new StatModificationList(buff), "Iron Boots", "Some boots made of Iron.", reqs, 1001, 35);
        }
    },
    BRONZE(1002) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 15);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            return new Boot(Assets.PLACEHOLDER, new StatModificationList(buff), "Bronze Boots", "Some boots made of Bronze.", reqs, 1002, 25);
        }
    },
    STEEL(1003) {
        @Override
        public Boot createInstance() {
            StatModification buff = new StatModification(Stat.ARMOR_MODIFIER, 20);
            StatRequirement sReq = new StatRequirement(Stat.DEFENSIVE_RATING, 0);
            RequirementList reqs = new RequirementList();
            reqs.addRequirement(sReq);
            return new Boot(ImageLoader.loadImage("./res/items/steel_toe.png"), new StatModificationList(buff), "Steel Boots", "Some boots made of Steel.", reqs, 1003, 50);
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
