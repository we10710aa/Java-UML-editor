package uml;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class BasicRect extends BasicComponent {

    public static int HOLLOW =0;
    public static int SOLID=1;
    private int fillType=0;

    public BasicRect(int x,int y,int width,int height){
        super(x,y,width,height);
    }
    public BasicRect(Point p1,Point p2){
        this.minX = min(p1.x,p2.x);
        this.minY = min(p1.y,p2.y);
        this.width = abs(p1.x-p2.x);
        this.height = abs(p1.y-p2.y);
    }
    public BasicRect(Point p1,Point p2,int fillType){
        this(p1,p2);
        this.fillType = fillType;
    }

    public BasicRect(int x,int y,int width, int height, int fillType){
        super(x,y,width,height);
        this.fillType= fillType;

    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(fillType == HOLLOW){
            graphics2D.drawRect(minX,minY,width,height);
        }
        else if (fillType == SOLID){
            graphics2D.fillRect(minX,minY,width,height);
        }
        Font f = new Font("Dialog", Font.BOLD, 12);
    }
}
