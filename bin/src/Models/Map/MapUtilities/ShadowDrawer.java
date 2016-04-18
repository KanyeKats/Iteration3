package Models.Map.MapUtilities;

import Models.Entities.Entity;
import Models.Map.Terrain;
import Models.Map.Tile;
import Utilities.Constants;

import java.awt.*;

/**
 * Created by Aidan on 4/16/2016.
 */
public class ShadowDrawer {

    public static void drawShadow(Tile tile, Graphics g) {
        if(tile!=null && tile.getTerrain()!= Terrain.SKY){

            int x = (int)tile.getPixelPoint().getX();
            int y = (int)tile.getPixelPoint().getY();

            int w = (int)(Constants.TILE_WIDTH*.75);
            int h = (int)(Constants.TILE_WIDTH*.75);

            x += (Constants.TILE_WIDTH - w)/2;
            y += (Constants.TILE_HEIGHT - h)/2;



            g.setColor(new Color(0, 0,0, 100));
            g.fillOval(x, y, w, h);
//            g.fillRoundRect(x, y, w , h, w/2, h/2 );
        }
    }

    public static void drawComponent(Image image, Graphics g, int x, int y){

        int finalx = x + (Constants.TILE_WIDTH -  image.getWidth(null)) / 2;
        int finaly = y + (Constants.TILE_HEIGHT - image.getHeight(null)) / 2;
        g.drawImage(image, finalx, finaly, null);
    }

}
