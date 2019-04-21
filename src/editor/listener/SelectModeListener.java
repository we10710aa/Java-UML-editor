package editor.listener;

import editor.UmlEditorCanvas;
import uml.BasicRect;
import uml.UmlClass;
import uml.UmlComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectModeListener implements MouseListener {
    private Point pressedPoint;
    boolean touchOnComponent = false;
    private UmlComponent tempComponent;
    private UmlEditorCanvas umlEditorCanvas;

    public SelectModeListener(UmlEditorCanvas canvas){
        this.umlEditorCanvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_SELECT){return;}
        if(touchOnComponent) {
            tempComponent.onSelected();
            touchOnComponent = false;
        }
        umlEditorCanvas.repaint();
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_SELECT){return;}
        pressedPoint = e.getPoint();
        for(UmlComponent component : umlEditorCanvas.getEditorObjects()){
            component.onUnSelected();
            if(component.contains(pressedPoint)){
                touchOnComponent = true;
                tempComponent = component;
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { //choose multiple stuff
        if(umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_SELECT){return;}
        if(!e.getPoint().equals(pressedPoint)){
            if(touchOnComponent){
                BasicRect rect = tempComponent.getBound();
                tempComponent.moveTo(new Point(e.getX()-(pressedPoint.x-rect.minX),
                        e.getY()-(pressedPoint.y-rect.minY)));
                touchOnComponent =false;
            }
            else{
                BasicRect  boundRect = new BasicRect(pressedPoint,e.getPoint());
                for(UmlComponent component:umlEditorCanvas.getEditorObjects()){
                    component.onUnSelected();
                    if(component.withIn(boundRect)){
                        component.onSelected();
                    }
                }
            }
            umlEditorCanvas.repaint();
        }
    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}