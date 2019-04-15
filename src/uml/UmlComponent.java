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
        return false;
    }

    public BasicRect getBound(){
        return new BasicRect(minX,minY,width,height);
    }

    public boolean withIn(BasicRect rect){
        return false;
    }

    public void draw(Graphics2D graphics2D){

    }

    public void moveTo(int x, int y){
        this.minX = x;
        this.minY = y;
    }

    public void onSelected(){
    }

    public  void onUnSelected(){

    }
}
