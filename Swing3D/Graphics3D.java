package Swing3D;

import java.awt.*;
import java.util.ArrayList;

//TODO: fix zooming past FOV z coord

/**
 *  rendered of 3D object on the screen
 */
public class Graphics3D {

    /**
     * When rendering on the screen we have to consider the field of view for better illusion of 3D space
     * @param FOV array of x,y,z points which define the position of the viewer
     */
    public Graphics3D(int[] FOV){
        xpoly = new int[4];
        ypoly = new int[4];
        this.FOV = FOV;
    }
    /**
     * 
     * @param FOV array of x,y,z points which define the position of the viewer
     * @param g2d 2D graphics object which we will ultimately use for drawing
     */
    public Graphics3D(int[] FOV, Graphics2D g2d){
        this(FOV);
        setGraphics(g2d);
    }
    
    /**
     * Since this library is build up on top of javax.swing we use swing graphics objects
     * @param g2d Swing graphics object
     */
    public void setGraphics(Graphics2D g2d){ this.g2d = g2d; }


    /**
     *  Overloaded method draw which draws Rectangles
     * @param obj rectangle to be drawn
     */
    public void draw(SRectangle obj){
        points = obj.getPoints();
        g2d.setColor(obj.getColor());

        for(int i = 0; i !=4; ++i){
            double z = points[i].getZ();
            xpoly[i] = transX(points[i].getX(),z);
            ypoly[i] = transY(points[i].getY(),z);

        }    
        g2d.drawPolygon(xpoly, ypoly, 4);    
    }

    /**
     *  Overloaded method fill which draws non translucent Rectangles
     * @param obj rectangle to be drawn
     */
    public void fill(SRectangle obj){
        points = obj.getPoints();
        g2d.setColor(obj.getColor());

        for(int i = 0; i !=4; ++i){
            double z = points[i].getZ();
            xpoly[i] = transX(points[i].getX(),z);
            ypoly[i] = transY(points[i].getY(),z);

        }    
        g2d.fillPolygon(xpoly, ypoly, 4); 
    }

    /**
     *  Overloaded method draw which draws Cuboids
     * @param obj Cuboid to be drawn
     */
    public void draw(SCuboid obj){
        g2d.setColor(obj.getColor());
        for(SRectangle r : obj.getWalls()){
            draw(r);
        }
    }

    /**
     *  Overloaded method fill which draws nontranslucent Cuboids
     * @param obj Cuboid to be drawn
     */
    public void fill(SCuboid obj){
        g2d.setColor(obj.getColor());
        for(SRectangle r : obj.getWalls()){
            fill(r);
        }
    }

    /**
     *  Overloaded method draw which draws Cuboid, with choice of optimalization
     * @param obj Cuboid to be drawn
     * @param wall_ids walls that we want to draw
     */
    public void draw(SCuboid obj, int[] wall_ids){
        g2d.setColor(obj.getColor());
        for(int id : wall_ids){
            draw(obj.getWalls()[id]);
        }
    }

    /**
     *  Overloaded method fill which draws Cuboid, with choice of optimalization
     * @param obj Cuboid to be filled
     * @param wall_ids walls that we want to draw
     */
    public void fill(SCuboid obj, int[] wall_ids){
        g2d.setColor(obj.getColor());
        for(int id : wall_ids){
            fill(obj.getWalls()[id]);
        }
    }

    /**
     * draws group of 3D objects, 
     * wether a given object in group is filled or just drawn is determined in each object in the group separately 
     * @param group group to be displayed
     */
    public void drawGroup(SObjectGroup<?> group){
        ArrayList<SRectangle> objects = group.getSortedObjects();
        for(SRectangle obj : objects){
            if(obj.isTranslucent()) draw(obj);
            else fill(obj);
        }
    }

    // calculates the ratio by which an object is to be shrinked based on field of view
    private double ratio(double z){
        return Math.abs((double)FOV[2]/ (FOV[2] - z)); 
    }

    // moves X coordinte so it creates the perception of distance
    private int transX(double x, double z){
        if(z == 0) return (int)Math.round(x);
        if(z >= FOV[2]) return ((z - FOV[0]) < 0) ? 0 : FOV[0]*2;

        final double P_CONSTANT = ratio(z); // FOV

        x += (x - FOV[0])*P_CONSTANT + FOV[0] - x;

        return (int)Math.round(x);
    }
    // moves Y coordinte so it creates the perception of distance
    private int transY(double y, double z){
        if(z == 0) return (int)Math.round(y);
        if(z >= FOV[2]) return ((y - FOV[1]) < 0) ? 0 : FOV[1]*2;

        final double P_CONSTANT = ratio(z); // FOV
         y += (y - FOV[1])*P_CONSTANT + FOV[1] - y;
        return (int)Math.round(y);
    }

    private int[] xpoly;
    private int[] ypoly; 
    private SPoint[] points;
    private int[] FOV;
    private Graphics2D g2d; 
    
}
