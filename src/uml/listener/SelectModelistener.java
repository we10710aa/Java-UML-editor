package uml.listener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class SelectModelistener implements MouseListener {
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
