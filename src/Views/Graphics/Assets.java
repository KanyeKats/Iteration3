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
    public static BufferedImage PLACEHOLDER;

    public static BufferedImage PLAYER_NORTH;
    public static BufferedImage PLAYER_SOUTH;
    public static BufferedImage PLAYER_NORTH_EAST;
    public static BufferedImage PLAYER_SOUTH_EAST;
    public static BufferedImage PLAYER_SOUTH_WEST;
    public static BufferedImage PLAYER_NORTH_WEST;

    public static BufferedImage ITEM_BAG;

    private static final int SPRITE_SIZE = 30;

    public static void init(){

        ImageLoader imageLoader = new ImageLoader();

        // TODO Put all terrains on a single spritsheet
        SpriteSheet terrainSheet = new SpriteSheet(imageLoader.loadImage("./res/map/terrain/earth.png"));
        EARTH = terrainSheet.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet water = new SpriteSheet(imageLoader.loadImage("./res/map/terrain/water.png"));
        WATER = water.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet sky = new SpriteSheet(imageLoader.loadImage("./res/map/terrain/sky.png"));
        SKY = sky.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        // TODO: Put all players on a single spritesheet.
        SpriteSheet north = new SpriteSheet(imageLoader.loadImage("./aiden_res/Entities/Player/PlayerN.png"));
        PLAYER_NORTH= north.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet south = new SpriteSheet(imageLoader.loadImage("./aiden_res/Entities/Player/PlayerS.png"));
        PLAYER_SOUTH = south.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet ne = new SpriteSheet(imageLoader.loadImage("./aiden_res/Entities/Player/PlayerNE.png"));
        PLAYER_NORTH_EAST = ne.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet nw = new SpriteSheet(imageLoader.loadImage("./aiden_res/Entities/Player/PlayerNW.png"));
        PLAYER_NORTH_WEST = nw.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet se = new SpriteSheet(imageLoader.loadImage("./aiden_res/Entities/Player/PlayerSE.png"));
        PLAYER_SOUTH_EAST = se.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet sw = new SpriteSheet(imageLoader.loadImage("./aiden_res/Entities/Player/PlayerSW.png"));
        PLAYER_SOUTH_WEST = sw.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        // TODO: Init all the areaEffect assets.

        // TODO: Init all the item assets.

        // TODO: Init all the effect assets.
        SpriteSheet placeholderSheet = new SpriteSheet(imageLoader.loadImage("./res/etc/placeholder-blk.png"));
        PLACEHOLDER = placeholderSheet.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));
    }
}
