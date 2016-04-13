package Utilities;

import Models.Entities.Entity;
import Models.Map.Direction;
import Models.Map.Map;
import Models.Map.Tile;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * Created by Aidan on 4/8/2016.
 */
public class MapNavigationUtilities {

    //finds tiles adjacent to entity
    public static ArrayList<Tile> findNeighbors(Point3D point, Map map){
        ArrayList<Tile> neighbors = new ArrayList<>();
        neighbors.add(map.getTile(point.add(1,-1,0)));
        neighbors.add(map.getTile(point.add(0,-1,0)));
        neighbors.add(map.getTile(point.add(-1,0,0)));
        neighbors.add(map.getTile(point.add(-1,1,0)));
        neighbors.add(map.getTile(point.add(0,1,0)));
        neighbors.add(map.getTile(point.add(1,0,0)));
        return neighbors;

    }

    public static ArrayList<ArrayList<Tile>> getTilesinPlane(Point3D point, int range, Map map){

        ArrayList<ArrayList<Tile>> resultTiles = new ArrayList<>();
        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];

        for(int i = -range; i <= range; i++) {
            ArrayList<Tile> tempList = new ArrayList<>();
            for (int j = -range; j <= range; j++) {
                for (int k = -range; k <= range; k++) {
                    point4Dend[0] = point4Dstart[0] - i;
                    point4Dend[1] = point4Dstart[1] - j;
                    point4Dend[2] = point4Dstart[2] - k;
                    if((point4Dend[0] + point4Dend[1] + point4Dend[2]) == 0){
                        Point3D newpoint = convertCubictoAxial(point4Dend);
                        Tile tile = map.getTile(newpoint);
                        if(tile != null) {
                            tempList.add(tile);
                        }
                    }
                }
            }
            resultTiles.add(tempList);
        }
        return resultTiles;
    }

    public static ArrayList<ArrayList<Tile>> getTilesInAngularPlane(Point3D point, int range, Map map, Direction direction) {
        Point3D pt = direction.getPointAdjacentTo(point);
        Direction leftDir = rotateEnum(4, direction);
        Direction rightDir = rotateEnum(2, direction);
        ArrayList<ArrayList<Tile>> resultTiles = new ArrayList<>();

        for (int i = 1; i < range; i++) {

            ArrayList<Tile> tempTiles = new ArrayList<>();
            tempTiles.add(map.getTile(pt));

            //set up for expansion on both sides
            Point3D leftSide = pt; //curr point + 4
            Point3D rightSide = pt; //curr point + 2

            //if even then expand out i/2 in both directions
            //if odd expand out i/2 (truncated) in both directions
            for (int j = 0; j < i/2; j++) {
                leftSide = leftDir.getPointAdjacentTo(leftSide);
                rightSide = rightDir.getPointAdjacentTo(rightSide);
                tempTiles.add(map.getTile(leftSide));
                tempTiles.add(map.getTile(rightSide));
            }

            //move to the next radius
            point = direction.getPointAdjacentTo(point);
            resultTiles.add(tempTiles);
        }
        return resultTiles;
    }

    //rotate the enum given an integer
    private static Direction rotateEnum(int i, Direction direction) {
        Direction d = direction.values()[direction.ordinal() + i % 5];
        return d;
    }

    public static double[] convertAxialtoCuubic(Point3D axialPoint){

         double[] cubicPoint = new double[4];
        cubicPoint[0] = axialPoint.getX();
        cubicPoint[1] = -axialPoint.getY() - axialPoint.getX();
        cubicPoint[2] = axialPoint.getY();
        cubicPoint[3] = axialPoint.getZ();
        return cubicPoint;
    }

    public static Point3D convertCubictoAxial(double[] cubicPoint){
        return new Point3D(cubicPoint[0],cubicPoint[2],cubicPoint[3]);
    }

    public static double distanceBetweenPointsSamePlane(double a[], double b[]){
        double [] resultPoint = new double [4];
        double dx = Math.abs(a[0] - b[0]);
        double dy = Math.abs(a[1] - b[1]);
        double dz = Math.abs(a[2] - b[2]);
        return (dx + dy + dz)/2;
    }



}
