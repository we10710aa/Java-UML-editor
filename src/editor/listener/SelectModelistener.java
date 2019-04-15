package editor.listener;

import editor.UmlEditorCanvas;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectModelistener implements MouseListener {
    private UmlEditorCanvas umlEditorCanvas;
    public SelectModelistener(){
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        Point point =  e.getPoint();
        System.out.println(point.x+","+point.y);
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
