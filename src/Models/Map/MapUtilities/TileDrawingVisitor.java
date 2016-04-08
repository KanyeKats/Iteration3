package Models.Map.MapUtilities;

import Models.Map.Tile;

import java.awt.image.BufferedImage;

/**
 * Created by Bradley on 4/7/16.
 */
public class TileDrawingVisitor {

    public BufferedImage accept(Tile tile) {

        // Create the base tile image via its terrain image.
        BufferedImage tileImage = tile.getTerrain().getImage();

        // TODO: Draw the entity, items, etc too.
        return tileImage;
    }
}
