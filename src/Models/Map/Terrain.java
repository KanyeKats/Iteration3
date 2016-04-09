package Models.Map;

import Views.Graphics.Assets;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by johnkaufmann on 3/31/16.
 */
public enum Terrain {
    EARTH(){
        @Override
        public BufferedImage getImage() {
            return Assets.EARTH;
        }
    },
    SKY(){
        @Override
        public BufferedImage getImage() {
            return Assets.SKY;
        }
    },
    WATER(){
        @Override
        public BufferedImage getImage() {
            return Assets.WATER;
        }
    };

    public abstract BufferedImage getImage();

    // Checks if this current terrain is in a passed in list of terrains
    public boolean inList(ArrayList<Terrain> terrains) {
        for (Terrain t: terrains) {
            if (t == this) {
                return true;
            }
        }
        return false;
    }
}
