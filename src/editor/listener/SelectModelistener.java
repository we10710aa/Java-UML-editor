package editor.listener;

import editor.UmlEditorCanvas;
import uml.UmlClass;
import uml.UmlComponent;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;

public class SelectModelistener implements MouseListener {
    private UmlEditorCanvas umlEditorCanvas;
    LinkedList<UmlComponent> objectList;

    public SelectModelistener(UmlEditorCanvas umlEditorCanvas){
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point =  e.getPoint();
        objectList.add(new UmlClass(point.x,point.y));
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }
}
