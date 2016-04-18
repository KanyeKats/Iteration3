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

    public static BufferedImage BUTTERFLY;
    public static BufferedImage CATERPILLAR;

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
    public static BufferedImage FEAR;
    public static BufferedImage SLEEP;
    public static BufferedImage POLYMORPH;

    public static BufferedImage THROWINGSTAR;

    private static final int SPRITE_SIZE = 70;
    private static final int DECAL_SIZE = 30;


    public static void init(){

//        ImageLoader imageLoader = new ImageLoader();

        // TODO Put all terrains on a single spritsheet
        SpriteSheet terrainSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/earth.png"));
        EARTH = terrainSheet.scale(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet water = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/water.png"));
        WATER = water.scale(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        SpriteSheet sky = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/sky.png"));
        SKY = sky.scale(0, 0, Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT * 1.25));

        // TODO: Put all players on a single spritesheet.
        SpriteSheet north = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerN.png"));
        PLAYER_NORTH= north.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet south = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerS.png"));
        PLAYER_SOUTH = south.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet ne = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerNE.png"));
        PLAYER_NORTH_EAST = ne.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet nw = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerNW.png"));
        PLAYER_NORTH_WEST = nw.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet se = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerSE.png"));
        PLAYER_SOUTH_EAST = se.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet sw = new SpriteSheet(ImageLoader.loadImage("./aiden_res/Entities/Player/PlayerSW.png"));
        PLAYER_SOUTH_WEST = sw.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);


        //BUG player
        SpriteSheet bugN = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugback.png"));
        BUG_NORTH = bugN.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrihgtup.png"));
        BUG_NORTH_EAST = bugNE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightdown.png"));
        BUG_SOUTH_EAST = bugSE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugS = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybug.png"));
        BUG_SOUTH = bugS.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftdown.png"));
        BUG_SOUTH_WEST = bugSW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftup.png"));
        BUG_NORTH_WEST = bugNW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugbackblue.png"));
        BUG_NORTH_BLUE = bugNBlue.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNEBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightupblue.png"));
        BUG_NORTH_EAST_BLUE = bugNEBlue.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSEBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightdownblue.png"));
        BUG_SOUTH_EAST_BLUE = bugSEBlue.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugsouthblue.png"));
        BUG_SOUTH_BLUE = bugSBlue.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSWBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftdownblue.png"));
        BUG_SOUTH_WEST_BLUE = bugSWBlue.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNWBlue = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftupblue.png"));
        BUG_NORTH_WEST_BLUE = bugNWBlue.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugbackGreen.png"));
        BUG_NORTH_GREEN = bugNGreen.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNEGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrihgtupgreen.png"));
        BUG_NORTH_EAST_GREEN = bugNEGreen.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSEGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugrightdowngreen.png"));
        BUG_SOUTH_EAST_GREEN = bugSEGreen.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugGreen.png"));
        BUG_SOUTH_GREEN = bugSGreen.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugSWGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftdowngreen.png"));
        BUG_SOUTH_WEST_GREEN = bugSWGreen.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet bugNWGreen = new SpriteSheet(ImageLoader.loadImage("./res/bugs/LAdybugleftupgreen.png"));
        BUG_NORTH_WEST_GREEN = bugNWGreen.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet beeN = new SpriteSheet(ImageLoader.loadImage("./res/bugs/BeeBack.png"));
        BEE_NORTH = beeN.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet beeNE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/BeeNorthRight.png"));
        BEE_NORTH_EAST = beeNE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet beeSE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/Beesouthright.png"));
        BEE_SOUTH_EAST = beeSE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet beeS = new SpriteSheet(ImageLoader.loadImage("./res/bugs/Bee.png"));
        BEE_SOUTH = beeS.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet beeSW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/Beesouthleft.png"));
        BEE_SOUTH_WEST = beeSW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet beeNW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/BeeNorthLeft.png"));
        BEE_NORTH_WEST = beeNW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet frogN = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogNorth.png"));
        FROG_NORTH = frogN.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet frogNE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogNorth right.png"));
        FROG_NORTH_EAST = frogNE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet frogSE = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogSouthright.png"));
        FROG_SOUTH_EAST = frogSE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet frogS = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogSouth.png"));
        FROG_SOUTH = frogS.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet frogSW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogSouthleft.png"));
        FROG_SOUTH_WEST = frogSW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet frogNW = new SpriteSheet(ImageLoader.loadImage("./res/bugs/frogNorth left.png"));
        FROG_NORTH_WEST = frogNW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        //mount
        SpriteSheet mountN = new SpriteSheet(ImageLoader.loadImage("./res/etc/handnorth.png"));
        MOUNT_NORTH = mountN.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet mountNE = new SpriteSheet(ImageLoader.loadImage("./res/etc/handnortheast.png"));
        MOUNT_NORTH_EAST = mountNE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet mountSE = new SpriteSheet(ImageLoader.loadImage("./res/etc/handsoutheast.png"));
        MOUNT_SOUTH_EAST = mountSE.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet mountS = new SpriteSheet(ImageLoader.loadImage("./res/etc/handsouth.png"));
        MOUNT_SOUTH = mountS.scale(0, 0,SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet mountSW = new SpriteSheet(ImageLoader.loadImage("./res/etc/handsouthwest.png"));
        MOUNT_SOUTH_WEST = mountSW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet mountNW = new SpriteSheet(ImageLoader.loadImage("./res/etc/handnorthwest.png"));
        MOUNT_NORTH_WEST = mountNW.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet butterfly = new SpriteSheet(ImageLoader.loadImage("res/bugs/Butterfly.png"));
        BUTTERFLY = butterfly.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        SpriteSheet caterpillar = new SpriteSheet(ImageLoader.loadImage("res/bugs/caterpillar.png"));
        CATERPILLAR = caterpillar.scale(0, 0, SPRITE_SIZE, (int)(SPRITE_SIZE));

        // Init all the areaEffect assets.
        SpriteSheet star = new SpriteSheet(ImageLoader.loadImage("./res/decals/gold-star.png"));
        LEVEL = star.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet heal = new SpriteSheet(ImageLoader.loadImage("./res/decals/red-cross.png"));
        HEAL = heal.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet dmg = new SpriteSheet(ImageLoader.loadImage("./res/decals/skull-and-crossbones.png"));
        DAMAGE = dmg.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet death = new SpriteSheet(ImageLoader.loadImage("./res/decals/skull-and-crossbones.png"));
        DEATH = death.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet tlprt = new SpriteSheet(ImageLoader.loadImage("./res/decals/teleport-sample.png"));
        TELEPORT = tlprt.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet trap = new SpriteSheet(ImageLoader.loadImage("./res/decals/trap-sample.png"));
        TRAP = trap.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet FLOW_nw = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_NW.png"));
        FLOW_NW = FLOW_nw.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet FLOW_ne = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_NE.png"));
        FLOW_NE = FLOW_ne.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet FLOW_n = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_N.png"));
        FLOW_N = FLOW_n.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet FLOW_sw = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_SW.png"));
        FLOW_SW = FLOW_sw.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        SpriteSheet FLOW_se = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_SE.png"));
        FLOW_SE= FLOW_se.scale(0, 0, SPRITE_SIZE, SPRITE_SIZE);

        SpriteSheet FLOW_s = new SpriteSheet(ImageLoader.loadImage("./res/decals/flow_S.png"));
        FLOW_S= FLOW_s.scale(0, 0, DECAL_SIZE, DECAL_SIZE);

        // TODO: Init all the item assets.
        SpriteSheet bag = new SpriteSheet(ImageLoader.loadImage("./res/items/bag.png"));
        ITEM_BAG = bag.scale(0, 0, DECAL_SIZE, DECAL_SIZE);


        // TODO: Init all the effect assets.
        SpriteSheet fireSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/fire.png"));
        FIRE = fireSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

        SpriteSheet lightningSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/lightning.png"));
        LIGHTNING = lightningSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

        SpriteSheet sparksSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/sparks.png"));
        SPARKS = sparksSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

        SpriteSheet fearSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/fear.png"));
        FEAR = fearSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

        SpriteSheet sleepSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/sleep.png"));
        SLEEP = sleepSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

        SpriteSheet polymorphSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/polymorph.png"));
        POLYMORPH = polymorphSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

        SpriteSheet placeholderSheet = new SpriteSheet(ImageLoader.loadImage("./res/etc/placeholder-blk.png"));
        PLACEHOLDER = placeholderSheet.scale(0, 0, DECAL_SIZE, (int)(DECAL_SIZE));

        //Fog Tiles
        SpriteSheet earthShroudedSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/earthShrouded.png"));
        EARTHSHROUDED = earthShroudedSheet.scale(0,0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT *1.25));

        SpriteSheet waterShroudedSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/waterShrouded.png"));
        WATERSHROUDED = waterShroudedSheet.scale(0,0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT *1.25));

        SpriteSheet fullFogSheet = new SpriteSheet(ImageLoader.loadImage("./res/map/terrain/fullFog.png"));
        FULLFOG = fullFogSheet.scale(0,0,Constants.TILE_WIDTH, (int)(Constants.TILE_HEIGHT *1.25));

        SpriteSheet throwingStarSheet = new SpriteSheet((ImageLoader.loadImage("./res/items/ninjaprojectile.png")));
        THROWINGSTAR = throwingStarSheet.scale(0,0,DECAL_SIZE, DECAL_SIZE);

    }
}
