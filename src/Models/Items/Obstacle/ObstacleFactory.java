package Models.Items.Obstacle;

import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

/**
 * Created by rokas on 4/9/16.
 */
public enum ObstacleFactory {
    BOULDER(11000) {
        @Override
        public Obstacle createInstance() {
            return new Obstacle(ImageLoader.loadImage("./res/items/boulder.png"), "Boulder", "Prevents entity from stepping on this boulder.", 11000);
        }
    },
    DAVE(11001) {
        @Override
        public Obstacle createInstance() {
            return new Obstacle(ImageLoader.loadImage("./res/items/TheDave.png"), "Dave", "Prevents entity from stepping on Dave.", 11001);
        }
    };

    // Properties
    private int ID;

    // Constructor
    ObstacleFactory(int ID) {
        this.ID = ID;
    }

    // Getter
    public int getID() {
        return ID;
    }

    // Abstract method each enum value needs to implement to instantiate the correct instance.
    public abstract Obstacle createInstance();


    // Method to instantiate by an ID, used when loading a map.
    // Loop through all enum values
    // Check if the enum's ID is equal to the passed in ID,
    // If so, return an instance of that enum
    // Otherwise, return null, an invalid ID was passed in
    public static Obstacle obstacleFromID(int id) {
        for (ObstacleFactory obstacle : values()) {
            if (obstacle.getID() == id) {
                return obstacle.createInstance();
            }
        }
        return null;
    }
}
