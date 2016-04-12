package Models.Map.MapUtilities;

import Models.Map.Tile;
import Utilities.Constants;
import javafx.geometry.Point3D;

import java.awt.*;

/**
 * Created by sergiopuleri on 4/11/16.
 */
public class MapUtilities {

    public static int distanceBetweenPoints(Point pointA, Point pointB) {
        // Get x coordinates
        int x1 = (int)pointA.getX();
        int x2 = (int)pointB.getX();

        // Get y coordinates
        int y1 = (int)pointA.getY();
        int y2 = (int)pointB.getY();

        // Pre-calculus yay!!!
        double distance = Math.sqrt((x1-x2)*(x1-x2) + (y1-y2)*(y1-y2));

        return (int)distance;
    }

    public static Point to2DPoint(Point3D point) {
        int x = (int)point.getX();
        int y = (int)point.getY();
        return new Point(x, y);
    }

    public static boolean isAtTargetPoint(Point currentPoint, Point targetPoint, int xRate , int yRate) {
        // Get current coordinates
        int currentX = (int)currentPoint.getX();
        int currentY = (int)currentPoint.getY();

        // Get target coordinates
        int targetX = (int)targetPoint.getX();
        int targetY = (int)targetPoint.getY();

        // Check x position
        boolean atX = false;
        if (xRate < 0) {
            if (currentX <= targetX) {
                atX = true;
            }
        } else {
            if (currentX >= targetX) {
                atX = true;
            }
        }

        // Check y position
        boolean atY = false;
        if (yRate < 0) {
            if (currentY <= targetY) {
                atY = true;
            }
        } else {
            if (currentY >= targetY) {
                atY = true;
            }
        }
        return atX && atY;
    }

    public static boolean isInBoundsOfTile(Point point, Tile target) {
        // Get target tiles pixel point location
        Point tilePoint = target.getPixelPoint();

        // Get coordinates of the pixel bounds of the tile
        int tileStartX = (int)tilePoint.getX();
        int tileStartY = (int)tilePoint.getY();
        int tileEndX =  tileStartX + Constants.TILE_WIDTH;
        int  tileEndY = tileStartY + Constants.TILE_HEIGHT;

        // Get the entitys current pixel coordinates
        int pointX = (int)point.getX();
        int pointY = (int)point.getY();

        // init booleans
        boolean inXBound = false;
        boolean inYBound = false;

        // Check if in x bounds and in y bounds of target tile's pixel bounds.
        if (pointX >= tileStartX && pointX <= tileEndX) {
            inXBound = true;
        }
        if (pointY <= tileEndY && pointY >= tileStartY) {
            inYBound = true;
        }

        return inXBound && inYBound;
    }



}
