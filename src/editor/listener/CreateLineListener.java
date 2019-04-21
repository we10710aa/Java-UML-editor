package editor.listener;

import editor.UmlEditorCanvas;
import uml.*;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateLineListener implements MouseListener {
    private Point pressedPoint;
    boolean touchOnComponent = false;
    private UmlComponent tempComponent;
    private UmlEditorCanvas umlEditorCanvas;

    public CreateLineListener(UmlEditorCanvas umlEditorCanvas){
        this.umlEditorCanvas = umlEditorCanvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if((umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_ASSOCIATION_LINE)&&
                (umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_COMPOSITION_LINE&&
                        (umlEditorCanvas.getMode()!= UmlEditorCanvas.MODE_GENERALIZATION_LINE))){return;}
        pressedPoint = e.getPoint();
        for(UmlComponent component : umlEditorCanvas.getEditorObjects()){
            if(component.contains(pressedPoint)){
                touchOnComponent = true;
                tempComponent = component.getUmlComponent(e.getPoint());
            }
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if((umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_ASSOCIATION_LINE)&&
                (umlEditorCanvas.getMode() != UmlEditorCanvas.MODE_COMPOSITION_LINE&&
                        (umlEditorCanvas.getMode()!= UmlEditorCanvas.MODE_GENERALIZATION_LINE))){return;}
        UmlComponent lastTouced = null;
        for(UmlComponent component:umlEditorCanvas.getEditorObjects()){
            if(component.contains(e.getPoint())){
                lastTouced = component.getUmlComponent(e.getPoint());
            }
        }
        if(touchOnComponent&&lastTouced!=null&& !lastTouced.equals(tempComponent)){
            switch (umlEditorCanvas.getMode()){
                case(UmlEditorCanvas.MODE_ASSOCIATION_LINE):
                    umlEditorCanvas.getConnectionLines().addLast(new UmlConnectionLine(tempComponent,lastTouced));
                    break;
                case(UmlEditorCanvas.MODE_GENERALIZATION_LINE):
                    umlEditorCanvas.getConnectionLines().addLast(new UmlGeneralizationLine(tempComponent,lastTouced));
                    break;
                case(UmlEditorCanvas.MODE_COMPOSITION_LINE):
                    umlEditorCanvas.getConnectionLines().addLast(new UmlCompositionLine(tempComponent,lastTouced));
                    break;
                default:
                    umlEditorCanvas.getConnectionLines().addLast(new UmlConnectionLine(tempComponent,lastTouced));
                    break;
            }
            touchOnComponent=false;
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
