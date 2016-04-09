package Models.Map.MapUtilities;

import javafx.geometry.Point3D;

import java.util.Comparator;

/**
 * Created by Bradley on 4/7/16.
 */
public class TileComparator implements Comparator<Point3D> {

    @Override
    public int compare(Point3D o1, Point3D o2) {

        // Check the Zs, as lower tiles need to be rendered first.
        int zComparison = Double.compare(o1.getZ(), o2.getZ());
        if(zComparison != 0){
            return zComparison;
        }

        // Y is the next most important ordering criterion.
        int yComparison = Double.compare(o1.getY(), o2.getY());
        if(yComparison != 0){
            return yComparison;
        }

        // If z and y comparisons returned 0, return the result of the x comparison.
        return Double.compare(o1.getX(), o2.getX());
    }

    @Override
    public boolean equals(Object obj) {
        return false;
    }
}
