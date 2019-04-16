package uml;

import uml.shape.BasicRect;

import java.awt.*;

public class UmlComponent {

    int minX;
    int minY;
    int width;
    int height;
    boolean selected;
    UmlComponent(){
    }

    public boolean contains(int x, int y){
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

    public void draw(Graphics2D graphics2D){

    }

    public void moveTo(int x, int y){
        this.minX = x;
        this.minY = y;
    }

    public void onSelected(){
        selected = true;
    }

    public  void onUnSelected(){
        selected = false;
    }
}
