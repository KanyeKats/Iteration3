package Utilities.MapUtilities;

import Models.Entities.Skills.InfluenceEffect.Effect;
import Models.Items.Item;
import Models.Map.AreaEffect;
import Models.Map.Tile;
import Utilities.Constants;
import Views.Graphics.Assets;
import Views.Graphics.ImageLoader;

import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

/**
 * Created by Bradley on 4/7/16.
 */
public class TileDrawingVisitor {

    public BufferedImage accept(Tile tile, boolean isInSight) {

        // Create the terrain tile image
        BufferedImage tileImage = ImageLoader.copyImage(tile.getTerrain().getImage(isInSight, tile.wasVisited()));
        if(tileImage == null){
            return null;
        }
        Graphics g = tileImage.getGraphics();

        // Extract the graphics object rom the tile image.

        // Draw the areaEffect
        AreaEffect areaEffect = tile.getAreaEffect();
        if(areaEffect!=null && areaEffect.getImage()!=null && isInSight && areaEffect.getVisibility()){
            drawComponent(areaEffect.getImage(), g);
        }

        // Draw the items
        ArrayList<Item> items = tile.getItems();
        if(!items.isEmpty() && isInSight){
            if (items.size() > 1){
                drawComponent(Assets.ITEM_BAG, g);
            }else{
                drawComponent(items.get(0).getImage(), g);
            }
        }

        // Draw the effects
        Effect effect = tile.getEffect();
        if(effect!=null && effect.getImage()!=null && isInSight){
            drawComponent(effect.getImage(), g);
        }

        g.dispose();
        return tileImage;
    }

    private void drawComponent(Image image, Graphics g){

        int x = Constants.TILE_WIDTH / 2 -  image.getWidth(null) / 2;
        int y = Constants.TILE_HEIGHT / 2 - image.getHeight(null) / 2;
        g.drawImage(image, x, y, null);
    }

}
