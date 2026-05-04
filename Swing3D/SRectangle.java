package Swing3D;

import java.awt.Color;

/**
 * The most basic 3D object - a rectangle, made out of four points
 */

public class SRectangle implements SObject{
    
    /**
     * Constructor takes four 3D points that create the rectangle
     * the points have to be in consecutive order, 
     * meaning if we input point a, ponit b, has to be that point that is directly connected to point a with a line
     * @param a top left 
     * @param b bottom left
     * @param c bottom right
     * @param d top right
     */
    public SRectangle(SPoint a,SPoint b,SPoint c,SPoint d){
        points = new SPoint[]{a,b,c,d};

    }

    public boolean isTranslucent(){ return this.translucent;}
    public void setTranslucent(boolean t){ this.translucent = t;}

    public double getZ(){return points[0].getZ();} // WARNING!!! in rendering, may not be compatible with other objects, since they return different z
    
    public SPoint[] getPoints(){ return points; }
    
    public void setColor(Color c){ this.color = c;}
    public Color getColor(){ return this.color;}
    
    /**
     * Rectangle has only one wall and its itself
     * @return returns itself
     */
    public SRectangle getWall(int ix){ return this;}

    /**
     * If the rectangle is part of a more complex 3D object, that object may assign id to the rectangle
     * @param id the id this rectangle will have
     */
    public void setWallID(int id){  this.wall_ID = (byte)id;}

    /**
     * 
     * @return the id of this rectangle, by default rectangle has id -1 sigalling id doesnt belong to any more complex 3D object
     */
    public int getWallID(){ return (int)wall_ID;}

    

    private byte wall_ID = -1;
    private Color color = Color.BLACK;
    private SPoint points[];
    private boolean translucent = true;
}
