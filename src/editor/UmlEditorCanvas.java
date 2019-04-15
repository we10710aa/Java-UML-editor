package editor;

import editor.listener.SelectModelistener;
import uml.UmlClass;
import uml.UmlComponent;
import uml.UmlContainer;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;


public class UmlEditorCanvas extends Canvas {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT =1000;
    public static int MODE_SELECT = 1;
    private UmlContainer editorObjects;
    public UmlEditorCanvas(){
        Frame f= new Frame("Canvas Editor");
        f.setLayout(null);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setBackground(Color.WHITE);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        f.add(this);
        f.setVisible(true);
        this.addMouseListener(new CreateClassListener());
        editorObjects = new UmlContainer();
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        editorObjects.draw(g2);
    }



    public void changeMode(int mode){
        if(mode == MODE_SELECT){
            this.getMouseListeners()[0] = new SelectModelistener(this);
        }
    }

    class CreateClassListener implements MouseListener{

        @Override
        public void mouseClicked(MouseEvent e) {
            UmlClass umlClass = new UmlClass(e.getX(),e.getY());
            editorObjects.add(umlClass);
            umlClass.draw((Graphics2D) getGraphics());
        }

        @Override
        public void mousePressed(MouseEvent e) { }

        @Override
        public void mouseReleased(MouseEvent e) { }

        @Override
        public void mouseEntered(MouseEvent e) { }

        @Override
        public void mouseExited(MouseEvent e) { }
    }
}
