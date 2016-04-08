package Views.Graphics;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

/**
 * Created by Aidan on 4/6/2016.
 */
public class ImageLoader {

    public BufferedImage loadImage(String path) {
        try {
            return ImageIO.read(new File(path));
//            return ImageIO.read(getClass().getResourceAsStream(path));
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        } catch (IllegalArgumentException e){
            e.printStackTrace();
        }
        System.out.println("Failed");
        return null;
    }
}
