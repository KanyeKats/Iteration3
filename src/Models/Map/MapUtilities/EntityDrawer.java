package Models.Map.MapUtilities;

import Models.Entities.Entity;
import Utilities.Constants;


import java.awt.*;


/**
 * Created by sergiopuleri on 4/10/16.
 */
public class EntityDrawer {

    public static void drawEntity(Entity entity, Graphics g) {
        if(entity!=null && entity.getImage()!=null){
            int x = (int)entity.getPixelLocation().getX();
            int y = (int)entity.getPixelLocation().getY();
            Graphics2D g2 = (Graphics2D)g;

            if (!entity.isVisible()) {
                g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.2f));
            }
            drawComponent(entity.getImage(), g2, x , y);
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER));

        }
    }

    public static void drawComponent(Image image, Graphics2D g, int x, int y){

        int finalx = x + (Constants.TILE_WIDTH -  image.getWidth(null)) / 2;
        int finaly = y + (Constants.TILE_HEIGHT - image.getHeight(null)) / 2;
        g.drawImage(image, finalx, finaly, null);
    }
}
