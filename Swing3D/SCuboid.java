package Swing3D;

import java.awt.Color;

/**
 * Cuboid madde out of 6 Rectangles
 * 
 *  IDs of the 6 rectangles
 *  0 -> back wall
 *  1 -> left wall
 *  2 -> bottom wa
 *  3 -> front wal
 *  4 -> right wal
 *  5 -> top wall
 */

public class SCuboid implements SObject{

    /**
     * 
     * @param x left coordinate
     * @param y top coordinate 
     * @param z back coordinate
     * @param width x range of the cuboid
     * @param height y range of the cuboid 
     * @param depth z range of the cuboid
     */

    public SCuboid(int x, int y, int z, int width, int height, int depth){
        this.x = x;
        this.y = y;
        this.z = z;
        this.height = height;
        this.width = width;
        this.depth = depth;

        initPoints();
        initWalls();
        //setAverageZ();
    }
    public boolean isTranslucent(){ return this.translucent;}
    public void setTranslucent(boolean t){ 
        this.translucent = t;
        for (SRectangle wall : walls){
            wall.setTranslucent(t);
        }
    }
    
    public SRectangle getWall(int ix){ return walls[ix];}
    public SPoint[] getPoints(){ return points;}
    /**
     * 
     * @return all the walls that create this cuboid
     */
    public SRectangle[] getWalls(){ return walls;}

    public void setColor(Color c){ this.color = c; for(SRectangle r : walls) r.setColor(color);}
    public Color getColor(){ return this.color;}

    public double getZ(){ return points[0].getZ();}

    private void initPoints(){
        points = new SPoint[8];

        points[0] = new SPoint(x, y, z);
        points[1] = new SPoint(x, y+height, z);
        points[2] = new SPoint(x+width, y, z);
        points[3] = new SPoint(x+width, y+height, z);
        points[4] = new SPoint(x, y, z+depth);
        points[5] = new SPoint(x, y+height, z+depth);
        points[6] = new SPoint(x+width, y, z+depth);
        points[7] = new SPoint(x+width, y+height, z+depth);

    }
    
    

    private void initWalls(){
        walls = new SRectangle[6];
        SRectangle back = new SRectangle(points[0], points[1],points[3],points[2]);
        back.setWallID(BACK);
        walls[0] =  back;

        SRectangle left = new SRectangle(points[0], points[1],points[5],points[4]); 
        left.setWallID(LEFT);
        walls[1] = left;

        SRectangle bot = new SRectangle(points[0], points[4],points[6],points[2]);
        bot.setWallID(BOT);
        walls[2] =  bot;

        SRectangle front = new SRectangle(points[7], points[5],points[4],points[6]);
        front.setWallID(FRONT);
        walls[3] =  front;

        SRectangle right = new SRectangle(points[7], points[3],points[2],points[6]);
        right.setWallID(RIGHT);
        walls[4] =  right;

        SRectangle top = new SRectangle(points[7], points[3],points[1],points[5]); 
        top.setWallID(TOP);
        walls[5] =  top;
    }


    private Color color = Color.BLACK;
    private SPoint[] points;
    private SRectangle[] walls;

    private int x,y,z;
    private int height, width, depth;

    private boolean translucent = false;

    /**
     *  The Cuboid is made out of 6 walls - rectangles,
     *  Every cuboid has the same ids for each wall,
     *  these are the static variables that are to be used when we want to access/render particular wall
     */
    public static final int FRONT = 3; 
    public static final int BACK = 0; 
    public static final int LEFT = 1; 
    public static final int RIGHT = 4; 
    public static final int TOP = 5; 
    public static final int BOT = 2; 

}