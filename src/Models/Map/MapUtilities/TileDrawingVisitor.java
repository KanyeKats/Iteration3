package Models.Map.MapUtilities;

import Models.Map.Tile;
import Utilities.Constants;
import Views.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/7/16.
 */
public class TileDrawingVisitor {

    public BufferedImage accept(Tile tile) {


        // Create the terrain tile image
        BufferedImage tileImage = ImageLoader.copyImage(tile.getTerrain().getImage());

        Graphics g = tileImage.getGraphics();

        // Extract the graphics object rom the tile image.
        // TODO: Draw items and areaEffects


        // Draw the entity
        Image entityImage = tile.getEntityImage();
        if(entityImage != null){
            int entityX = Constants.TILE_WIDTH / 2 - entityImage.getWidth(null)/2;
            int entityY = Constants.TILE_HEIGHT / 2  - entityImage.getHeight(null) / 2;

            g.drawImage(entityImage, entityX, entityY, null);
        }

        g.dispose();
        return tileImage;
    }
}
