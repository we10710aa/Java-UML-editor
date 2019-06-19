package editor.listener;

import editor.UmlEditorCanvas;
import uml.BasicRect;
import uml.UmlComponent;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class SelectModeListener extends MouseAdapter {
    private Point pressedPoint;
    private Dimension relativePosition;
    boolean touchOnComponent = false;
    private UmlComponent tempComponent;
    private UmlEditorCanvas umlEditorCanvas;

    public SelectModeListener(UmlEditorCanvas canvas) {
        this.umlEditorCanvas = canvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if (touchOnComponent) {
            tempComponent.onSelected();
            touchOnComponent = false;
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        pressedPoint = e.getPoint();
        for (UmlComponent component : umlEditorCanvas.getEditorObjects()) {
            component.onUnSelected();
            if (component.contains(pressedPoint)) {
                touchOnComponent = true;
                tempComponent = component;
                relativePosition = new Dimension(e.getX()-component.minX,e.getY()-component.minY);
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) { //choose multiple stuff
        if (!e.getPoint().equals(pressedPoint)) {
            if (touchOnComponent) {
                tempComponent.moveTo(new Point(e.getX()-relativePosition.width,e.getY()-relativePosition.height));
                touchOnComponent = false;
            } else {
                BasicRect boundRect = new BasicRect(pressedPoint, e.getPoint());
                for (UmlComponent component : umlEditorCanvas.getEditorObjects()) {
                    component.onUnSelected();
                    if (component.withIn(boundRect)) {
                        component.onSelected();
                    }
                }
            }
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        if (touchOnComponent) {
            tempComponent.moveTo(new Point(e.getX()-relativePosition.width,e.getY()-relativePosition.height));
        }

    }
}