package Utilities;

import Models.Entities.Entity;
import Models.Map.Map;
import Models.Map.Tile;
import javafx.geometry.Point3D;

import java.util.ArrayList;

/**
 * Created by Aidan on 4/8/2016.
 */
public class MapNavigationUtilities {

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

    public static ArrayList<Tile> getTilesinPlane(Point3D point, int range, Map map){

        ArrayList<Tile> resultTiles = new ArrayList<>();
        double[] point4Dstart = convertAxialtoCuubic(point);
        double[] point4Dend = new double[4];

        ArrayList<Tile> tilesInRange = new ArrayList<>();
        for(int i = -range; i < range; i++) {
            for (int j = -range; j < range; j++) {
                for (int k = -range; k < range; k++) {
                    point4Dend[0] = point4Dstart[0] - i;
                    point4Dend[1] = point4Dstart[1] - j;
                    point4Dend[2] = point4Dstart[2] - k;
                    if(point4Dend[0] + point4Dend[1] + point4Dend[2] == 0){
                        Point3D newpoint = convertCubictoAxial(point4Dend);
                        System.out.println(newpoint.toString());
                        Tile tileInRange = map.getTile(newpoint);
                        //if tile is not null because it is not on the map
                        tilesInRange.add(tileInRange);
                    }
                }
            }
        }
        return tilesInRange;
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
