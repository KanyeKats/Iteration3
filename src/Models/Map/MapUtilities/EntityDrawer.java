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
            drawComponent(entity.getImage(), g, x , y);
        }
    }

    public static void drawComponent(Image image, Graphics g, int x, int y){

        int finalx = x + (Constants.TILE_WIDTH -  image.getWidth(null)) / 2;
        int finaly = y + (Constants.TILE_HEIGHT - image.getHeight(null)) / 2;
        g.drawImage(image, finalx, finaly, null);
    }
}
