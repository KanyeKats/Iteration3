package Models.Map;

import javafx.geometry.Point3D;

/**
 * Created by johnkaufmann on 3/31/16.
 *
 */
public enum Direction {
    NORTH {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(0, -1, 0);
        }
    },
    NORTH_EAST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(1, -1, 0);
        }
    },
    SOUTH_EAST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(1, 0, 0);
        }
    },
    SOUTH {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(0, 1, 0);
        }
    },
    SOUTH_WEST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(-1, 1, 0);
        }
    },
    NORTH_WEST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            return p.add(-1, 0, 0);
        }
    };

    public abstract Point3D getPointAdjacentTo(Point3D point);



    public Direction next() {
        int length = values().length;
        int current = this.ordinal();
        if (current > length-1) {
            current = 0;
        }
        return values()[current + 1];
    }
    public Direction previous(){
        int length = values().length;
        int current = this.ordinal();
        if (current < 0) {
            current = length - 1;
        }
        return values()[current - 1];
    }

}
