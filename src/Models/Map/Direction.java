package Models.Map;

import javafx.geometry.Point3D;

/**
 * Created by johnkaufmann on 3/31/16.
 * TODO:
 */
public enum Direction {
    NORTH {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(0, -1, 0);
            return p;
        }
    },
    NORTH_EAST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(1, -1, 0);
            return p;
        }
    },
    SOUTH_EAST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(1, 0, 0);
            return p;
        }
    },
    SOUTH {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(0, 1, 0);
            return p;
        }
    },
    SOUTH_WEST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(-1, 1, 0);
            return p;
        }
    },
    NORTH_WEST {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(-1, 1, 0);
            return p;
        }
    },
    UP {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(0, 0, 1);
            return p.getZ() < 10 ? p : null;
        }
    },
    DOWN {
        public Point3D getPointAdjacentTo(Point3D point){
            Point3D p = new Point3D(point.getX(), point.getY(), point.getZ());
            p.add(0, 0, -1);
            return p.getZ() >= 0 ? p : null;
        }
    };

    public abstract Point3D getPointAdjacentTo(Point3D point);
}
