package Models.Map;

import Views.Graphics.Assets;

import java.awt.image.BufferedImage;

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
}
