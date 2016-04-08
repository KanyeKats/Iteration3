package Views.Graphics;

import Utilities.Constants;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Assets {

    public static BufferedImage EARTH;
    public static BufferedImage WATER;
    public static BufferedImage SKY;

    public static void init(){

        ImageLoader imageLoader = new ImageLoader();

        SpriteSheet terrainSheet = new SpriteSheet(imageLoader.loadImage("./res/map/terrain/earth.png"));
        EARTH = terrainSheet.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));
    }
}
