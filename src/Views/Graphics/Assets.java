package Views.Graphics;

import Utilities.Constants;

import java.awt.image.BufferedImage;
import javafx.scene.image.Image;

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

    //Fog
    public static BufferedImage FULLFOG;
    public static BufferedImage EARTHSHROUDED;
    public static BufferedImage WATERSHROUDED;

    // Area of Effects
    public static BufferedImage LEVEL;
    public static BufferedImage HEAL;
    public static BufferedImage DAMAGE;
    public static BufferedImage DEATH;
    public static BufferedImage TELEPORT;
    public static BufferedImage TRAP;





    private static final int SPRITE_SIZE = 30;


    public static void init(){

//        ImageLoader imageLoader = new ImageLoader();

        // TODO Put all terrains on a single spritsheet
        SpriteSheet terrainSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/earth.png"));
        EARTH = terrainSheet.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet water = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/water.png"));
        WATER = water.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet sky = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/sky.png"));
        SKY = sky.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        // TODO: Put all players on a single spritesheet.
        SpriteSheet north = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerN.png"));
        PLAYER_NORTH= north.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet south = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerS.png"));
        PLAYER_SOUTH = south.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet ne = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerNE.png"));
        PLAYER_NORTH_EAST = ne.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet nw = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerNW.png"));
        PLAYER_NORTH_WEST = nw.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet se = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerSE.png"));
        PLAYER_SOUTH_EAST = se.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet sw = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerSW.png"));
        PLAYER_SOUTH_WEST = sw.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        // Init all the areaEffect assets.
        SpriteSheet star = new SpriteSheet(ImageLoader.loadImage("./res/decals/gold-star.png"));
        LEVEL = star.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet heal = new SpriteSheet(ImageLoader.loadImage("./res/decals/red-cross.png"));
        HEAL = heal.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet dmg = new SpriteSheet(ImageLoader.loadImage("./res/decals/skull-and-crossbones.png"));
        DAMAGE = dmg.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet death = new SpriteSheet(ImageLoader.loadImage("./res/decals/skull-and-crossbones.png"));
        DEATH = death.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet tlprt = new SpriteSheet(ImageLoader.loadImage("./res/decals/teleport-sample.png"));
        TELEPORT = tlprt.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet trap = new SpriteSheet(ImageLoader.loadImage("./res/decals/trap-sample.png"));
        TRAP = trap.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        // TODO: Init all the item assets.

        // TODO: Init all the effect assets.


        SpriteSheet placeholderSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/placeholder-blk.png"));
        PLACEHOLDER = placeholderSheet.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        //Fog Tiles
        SpriteSheet earthShroudedSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/earthShrouded.png"));
        EARTHSHROUDED = earthShroudedSheet.crop(0,0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT *1.25));

        SpriteSheet waterShroudedSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/waterShrouded.png"));
        WATERSHROUDED = waterShroudedSheet.crop(0,0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT *1.25));

        SpriteSheet fullFogSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/fullFog.png"));
        FULLFOG = fullFogSheet.crop(0,0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT *1.25));

    }
}
