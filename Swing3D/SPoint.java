package Swing3D;


/**
 *  Building block of every 3D object
 */
public class SPoint {
    

    @Override
    public SPoint clone(){
        return new SPoint(coordinates[0],coordinates[1],coordinates[2]);
    }

    /**
     * 
     * @param x x coordinate 
     * @param y y coordinate
     * @param z z coordinate
     */
    public SPoint(double x, double y,double z){
        coordinates = new double[]{x,y,z};
    }
    /**
     *  getter methods
     * @return retrun given coordinate
     */
    public double getX() { return coordinates[0];}
    public double getY() { return coordinates[1];}
    public double getZ() { return coordinates[2];}
    public double get(int ix){ return coordinates[ix];}

    /**
     * setter methods
     * @param x,y,z new coordinate
     */
    public void setX(double x){ coordinates[0] = x;}
    public void setY(double y){ coordinates[1] = y;}
    public void setZ(double z){ coordinates[2] = z;}
    public void set(int ix, double v){ coordinates[ix] = v;}

    double coordinates[];
}
