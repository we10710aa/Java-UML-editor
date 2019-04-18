package editor;

import uml.UmlClass;
import uml.UmlComponent;
import uml.BasicRect;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.LinkedList;


public class UmlEditorCanvas extends Canvas {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT =1000;
    public static int MODE_SELECT = 1;
    private LinkedList<UmlComponent> editorObjects;
    public UmlEditorCanvas(){
        Frame f= new Frame("Canvas Editor");
        f.setLayout(null);
        f.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        this.setBackground(Color.WHITE);
        this.setSize(WINDOW_WIDTH,WINDOW_HEIGHT);
        f.add(this);
        f.setVisible(true);
        this.addMouseListener(new SelectModeListener());
        editorObjects = new LinkedList<>();
        editorObjects.add(new UmlClass(50,50));
        editorObjects.add(new UmlClass(50,250));
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(UmlComponent component :editorObjects){
            component.draw(g2);
        }
    }



    public void changeMode(int mode){
        if(mode == MODE_SELECT){
            this.getMouseListeners()[0] = new SelectModeListener();
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
    class SelectModeListener implements MouseListener{
        Point pressedPoint;
        boolean touchOnComponent = false;
        UmlComponent tempComponent;
        @Override
        public void mouseClicked(MouseEvent e) {
            System.out.println("OnMouseClicked");
            if(touchOnComponent){
            tempComponent.onSelected();
            touchOnComponent = false;
        }
        repaint();
    }

    @Override
        public void mousePressed(MouseEvent e) {
            System.out.println("OnMousePressed");
            pressedPoint = e.getPoint();
            for(UmlComponent component : editorObjects){
                component.onUnSelected();
                if(component.contains(pressedPoint)){
                    touchOnComponent = true;
                    tempComponent = component;
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) { //choose multiple stuff
            System.out.println("OnMouseReleased");
            if(!e.getPoint().equals(pressedPoint)){
                if(touchOnComponent){
                    BasicRect rect = tempComponent.getBound();
                    tempComponent.moveTo(new Point(e.getX()-(pressedPoint.x-rect.minX),
                            e.getY()-(pressedPoint.y-rect.minY)));
                    touchOnComponent =false;
                }
                else{
                    BasicRect  boundRect = new BasicRect(pressedPoint,e.getPoint());
                    for(UmlComponent component:editorObjects){
                        component.onUnSelected();
                        if(component.withIn(boundRect)){
                            component.onSelected();
                        }
                    }
                }
                repaint();
            }
        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }
}
