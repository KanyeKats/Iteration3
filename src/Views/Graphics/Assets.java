package Views.Graphics;

import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/6/2016.
 */
public class Assets {

    public static BufferedImage TEST;

    public static void init(){

        SpriteSheet TestSheet = new SpriteSheet(new ImageLoader().loadImage("/Textures/TEST.png"));
        TEST = TestSheet.crop(0, 0, 32, 32);

    }
}
