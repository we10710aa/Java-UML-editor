package uml;

import java.awt.*;

public class BasicLine extends  BasicComponent{
    public BasicLine(int x1,int y1,int x2,int y2){
        super(x1,y1,x2,y2);
    }

    public BasicLine(Point p1,Point p2){
        this(p1.x,p1.y,p2.x,p2.y);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawLine(minX,minY,width,height);
    }

    public void moveTo(Point p1,Point p2){
        super.moveTo(p1);
        this.width = p2.x;
        this.height = p2.y;
    }

}
