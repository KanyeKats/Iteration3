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

    //avatar
    public static BufferedImage BUG_NORTH;
    public static BufferedImage BUG_SOUTH;
    public static BufferedImage BUG_NORTH_EAST;
    public static BufferedImage BUG_SOUTH_EAST;
    public static BufferedImage BUG_SOUTH_WEST;
    public static BufferedImage BUG_NORTH_WEST;

    public static BufferedImage BUG_NORTH_BLUE;
    public static BufferedImage BUG_SOUTH_BLUE;
    public static BufferedImage BUG_NORTH_EAST_BLUE;
    public static BufferedImage BUG_SOUTH_EAST_BLUE;
    public static BufferedImage BUG_SOUTH_WEST_BLUE;
    public static BufferedImage BUG_NORTH_WEST_BLUE;

    public static BufferedImage BUG_NORTH_GREEN;
    public static BufferedImage BUG_SOUTH_GREEN;
    public static BufferedImage BUG_NORTH_EAST_GREEN;
    public static BufferedImage BUG_SOUTH_EAST_GREEN;
    public static BufferedImage BUG_SOUTH_WEST_GREEN;
    public static BufferedImage BUG_NORTH_WEST_GREEN;

    //Bee
    public static BufferedImage BEE_NORTH;
    public static BufferedImage BEE_SOUTH;
    public static BufferedImage BEE_NORTH_EAST;
    public static BufferedImage BEE_SOUTH_EAST;
    public static BufferedImage BEE_SOUTH_WEST;
    public static BufferedImage BEE_NORTH_WEST;

    //polymorphFrog
    public static BufferedImage FROG_NORTH;
    public static BufferedImage FROG_SOUTH;
    public static BufferedImage FROG_NORTH_EAST;
    public static BufferedImage FROG_SOUTH_EAST;
    public static BufferedImage FROG_SOUTH_WEST;
    public static BufferedImage FROG_NORTH_WEST;

    //mount
    public static BufferedImage MOUNT_NORTH;
    public static BufferedImage MOUNT_SOUTH;
    public static BufferedImage MOUNT_NORTH_EAST;
    public static BufferedImage MOUNT_SOUTH_EAST;
    public static BufferedImage MOUNT_SOUTH_WEST;
    public static BufferedImage MOUNT_NORTH_WEST;

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
    public static BufferedImage FLOW_NW;
    public static BufferedImage FLOW_NE;
    public static BufferedImage FLOW_SW;
    public static BufferedImage FLOW_SE;
    public static BufferedImage FLOW_N;
    public static BufferedImage FLOW_S;

    //Skill
    public static BufferedImage FIRE;
    public static BufferedImage LIGHTNING;
    public static BufferedImage SPARKS;

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


        //BUG player
        SpriteSheet bugN = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugback.png"));
        BUG_NORTH = bugN.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrihgtup.png"));
        BUG_NORTH_EAST = bugNE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightdown.png"));
        BUG_SOUTH_EAST = bugSE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugS = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybug.png"));
        BUG_SOUTH = bugS.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftdown.png"));
        BUG_SOUTH_WEST = bugSW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftup.png"));
        BUG_NORTH_WEST = bugNW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugbackblue.png"));
        BUG_NORTH_BLUE = bugNBlue.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNEBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightupblue.png"));
        BUG_NORTH_EAST_BLUE = bugNEBlue.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSEBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightdownblue.png"));
        BUG_SOUTH_EAST_BLUE = bugSEBlue.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugsouthblue.png"));
        BUG_SOUTH_BLUE = bugSBlue.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSWBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftdownblue.png"));
        BUG_SOUTH_WEST_BLUE = bugSWBlue.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNWBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftupblue.png"));
        BUG_NORTH_WEST_BLUE = bugNWBlue.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugbackGreen.png"));
        BUG_NORTH_GREEN = bugNGreen.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNEGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrihgtupgreen.png"));
        BUG_NORTH_EAST_GREEN = bugNEGreen.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSEGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightdowngreen.png"));
        BUG_SOUTH_EAST_GREEN = bugSEGreen.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugGreen.png"));
        BUG_SOUTH_GREEN = bugSGreen.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugSWGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftdowngreen.png"));
        BUG_SOUTH_WEST_GREEN = bugSWGreen.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet bugNWGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftupgreen.png"));
        BUG_NORTH_WEST_GREEN = bugNWGreen.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet beeN = new SpriteSheet(ImageLoader.loadImage("./res/bugs/BeeBack.png"));
        BEE_NORTH = beeN.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet beeNE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/BeeNorthRight.png"));
        BEE_NORTH_EAST = beeNE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet beeSE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/Beesouthright.png"));
        BEE_SOUTH_EAST = beeSE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet beeS = new SpriteSheet(ImageLoader.loadImage("./res/bugs/Bee.png"));
        BEE_SOUTH = beeS.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet beeSW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/Beesouthleft.png"));
        BEE_SOUTH_WEST = beeSW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet beeNW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/BeeNorthLeft.png"));
        BEE_NORTH_WEST = beeNW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet frogN = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogNorth.png"));
        FROG_NORTH = frogN.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet frogNE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogNorth right.png"));
        FROG_NORTH_EAST = frogNE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet frogSE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogSouthright.png"));
        FROG_SOUTH_EAST = frogSE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet frogS = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogSouth.png"));
        FROG_SOUTH = frogS.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet frogSW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogSouthleft.png"));
        FROG_SOUTH_WEST = frogSW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet frogNW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogNorth left.png"));
        FROG_NORTH_WEST = frogNW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        //mount
        SpriteSheet mountN = new SpriteSheet(ImageLoader.loadImage("./res/etc/handnorth.png"));
        MOUNT_NORTH = mountN.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet mountNE = new SpriteSheet(ImageLoader.loadImage("./res/etc/handnortheast.png"));
        MOUNT_NORTH_EAST = mountNE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet mountSE = new SpriteSheet(ImageLoader.loadImage("./res/etc/handsoutheast.png"));
        MOUNT_SOUTH_EAST = mountSE.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet mountS = new SpriteSheet(ImageLoader.loadImage("./res/etc/handsouth.png"));
        MOUNT_SOUTH = mountS.crop(0, 0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet mountSW = new SpriteSheet(ImageLoader.loadImage("./res/etc/handsouthwest.png"));
        MOUNT_SOUTH_WEST = mountSW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet mountNW = new SpriteSheet(ImageLoader.loadImage("./res/etc/handnorthwest.png"));
        MOUNT_NORTH_WEST = mountNW.crop(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

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

        SpriteSheet FLOW_nw = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_NW.png"));
        FLOW_NW = FLOW_nw.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet FLOW_ne = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_NE.png"));
        FLOW_NE = FLOW_ne.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet FLOW_n = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_N.png"));
        FLOW_N = FLOW_n.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet FLOW_sw = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_SW.png"));
        FLOW_SW = FLOW_sw.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet FLOW_se = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_SE.png"));
        FLOW_SE= FLOW_se.crop(0, 0, Constants.TILE_WIDTH, Constants.TILE_WIDTH);

        SpriteSheet FLOW_s = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_S.png"));
        FLOW_S= FLOW_s.crop(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        // TODO: Init all the item assets.
        SpriteSheet bag = new SpriteSheet(ImageLoader.loadImage("./res/items/bag.png"));
        ITEM_BAG = bag.crop(0, 0, Constants.TILE_WIDTH, Constants.TILE_WIDTH);


        // TODO: Init all the effect assets.
        SpriteSheet fireSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/fire.png"));
        FIRE = fireSheet.crop(0,0,Constants.TILE_WIDTH, Constants.TILE_WIDTH);

        SpriteSheet lightningSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/lightning.png"));
        LIGHTNING = lightningSheet.crop(0,0,Constants.TILE_WIDTH, Constants.TILE_WIDTH);

        SpriteSheet sparksSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/sparks.png"));
        SPARKS = sparksSheet.crop(0,0,Constants.TILE_WIDTH, Constants.TILE_WIDTH);

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
