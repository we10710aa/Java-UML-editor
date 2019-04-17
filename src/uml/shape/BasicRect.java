package uml.shape;

import java.awt.*;

import static java.lang.Math.abs;
import static java.lang.Math.min;

public class BasicRect {
    public int minX;
    public int minY;
    public int width;
    public int height;
    public BasicRect(int x,int y,int width, int height){
        this.minX = x;
        this.minY=y;
        this.width= width;
        this.height = height;
    }
    public BasicRect(Point p1,Point p2){
        this.minX = min(p1.x,p2.x);
        this.minY = min(p1.y,p2.y);
        this.width = abs(p1.x-p2.x);
        this.height = abs(p1.y-p2.y);
    }
}
