package uml;

import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import static java.util.Collections.max;
import static java.util.Collections.min;

public class UmlComposition extends UmlComponent {
    public LinkedList<UmlComponent> getChildList() {
        return childList;
    }

    private LinkedList<UmlComponent> childList;

    public UmlComposition(List<UmlComponent> list){
        this.childList = (LinkedList<UmlComponent>) list;
        checkBound();
    }

    private void checkBound(){
        List<Integer> childXs = new ArrayList<>();
        List<Integer> childYs = new ArrayList<>();
        for (UmlComponent component:childList){
            childXs.add(component.minX);
            childXs.add(component.minX+component.width);
            childYs.add(component.minY);
            childYs.add(component.height+component.minY);
        }
        this.minX = min(childXs);
        this.width = max(childXs)-minX;
        this.minY = min(childYs);
        this.height = max(childYs)-minY;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for(UmlComponent child:childList){
            child.draw(graphics2D);
        }
        if(selected){
            setConnectionPort();
            drawConnectionPort(graphics2D);
        }
    }

    @Override
    public boolean contains(Point p) {
        for(UmlComponent component : childList){
            if(component.contains(p)){
                return true;
            }
        }
        return false;
    }

    @Override
    public void moveTo(Point p) {
        int moveDistanceX = p.x - minX;
        int moveDistanceY = p.y - minY;

        for(UmlComponent component:childList){
            component.moveTo(new Point(component.minX+moveDistanceX,component.minY+moveDistanceY));
        }
        super.moveTo(p);
    }

    @Override
    public UmlComponent getUmlComponent(Point p1) {
        UmlComponent t=null;
        for (UmlComponent component:childList){
            if(component.contains(p1)){
                t= component.getUmlComponent(p1);
            }
        }
        return t;

    }
}
