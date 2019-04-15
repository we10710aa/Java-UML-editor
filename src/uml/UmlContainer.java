package uml;

import java.awt.*;
import java.util.LinkedList;

public class UmlContainer extends UmlComponent {
    private LinkedList<UmlComponent> childList;

    public UmlContainer(){
        childList = new LinkedList<>();
    }

    public void add(UmlComponent component){
        childList.add(component);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        for(UmlComponent child:childList){
            child.draw(graphics2D);
        }
    }
}
