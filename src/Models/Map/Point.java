//package Models.Map;
//
///**
// * Created by johnkaufmann on 3/31/16.
// *
// */
//public class Point {
//    private int x;
//    private int y;
//    private int z;
//
//    public Point(int x, int y, int z) {
//        this.x = x;
//        this.y = y;
//        this.z = z;
//    }
//
//    public int getX() {
//        return x;
//    }
//
//    public int getY() {
//        return y;
//    }
//
//    public int getZ() {
//        return z;
//    }
//
//    //moves point in 2d plane
//    public Point translate(Direction direction) {
//        if(this.x % 2 == 0) {
//            switch(direction) {
//                case Down:
//                    this.y++;
//                    break;
//                case Up:
//                    this.y--;
//                    break;
//                case UpLeft:
//                    this.x--;
//                    this.y--;
//                    break;
//                case UpRight:
//                    this.x++;
//                    this.y--;
//                    break;
//                case DownLeft:
//                    this.x--;
//                    break;
//                case DownRight:
//                    this.x++;
//                    break;
//            }
//        } else {
//            switch(direction) {
//                case Down:
//                    this.y++;
//                    break;
//                case Up:
//                    this.y--;
//                    break;
//                case DownLeft:
//                    this.x--;
//                    this.y++;
//                    break;
//                case DownRight:
//                    this.x++;
//                    this.y++;
//                    break;
//                case UpLeft:
//                    this.x--;
//                    break;
//                case UpRight:
//                    this.x++;
//                    break;
//            }
//        }
//    }
//
//    //moves point in 3d plane
//    public void translate(Direction verticalDirection, Direction horizontalDirection) {
//        translate(horizontalDirection);
//        switch (verticalDirection) {
//            case Up:
//                this.z++;
//                break;
//            case Down:
//                this.z--;
//                break;
//            default:
//                System.out.print("Something went wrong in" + this.toString());
//        }
//    }
//}
