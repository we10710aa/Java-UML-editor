package uml;

import uml.shape.BasicRect;

import java.awt.*;

public class UmlComponent {

    int minX;
    int minY;
    int width;
    int height;
    boolean selected;
    public UmlComponent(){
    }

    public boolean contains(Point p){
        int x = p.x;
        int y = p.y;
        return (x > minX)&&(y>minY)&&(x<minX+width)&&(y<minY+height);
    }

    public BasicRect getBound(){
        return new BasicRect(minX,minY,width,height);
    }

    public boolean withIn(BasicRect rect){
        return (minX>rect.minX)&&
                (minX+width<(rect.minX+rect.width))&&
                (minY>rect.minY)&&
                (minY+height<(rect.minY+rect.height));
    }

    public void draw(Graphics2D graphics2D){ }

    public void moveTo(Point p){
        this.minX = p.x;
        this.minY = p.y;
    }

    public void onSelected(){
        selected = true;
    }

    public  void onUnSelected(){
        selected = false;
    }
}
