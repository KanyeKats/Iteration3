package Views.Graphics;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Aidan on 4/6/2016.
 */
public class SpriteSheet {

    private BufferedImage sheet;

    public SpriteSheet(BufferedImage sheet) {
        this.sheet = sheet;
    }

    public BufferedImage scale(int x, int y, int width, int height) {
        BufferedImage resizedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_ARGB);
        Graphics g = resizedImage.getGraphics();
        g.drawImage(sheet, 0, 0, width, height, null);
        g.dispose();

        return resizedImage;
    }
}
