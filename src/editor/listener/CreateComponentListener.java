package editor.listener;

import editor.UmlEditorCanvas;
import uml.UmlClass;
import uml.UmlUseCase;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class CreateComponentListener extends MouseAdapter {

    private UmlEditorCanvas umlEditorCanvas;

    public CreateComponentListener(UmlEditorCanvas umlEditorCanvas) {
        this.umlEditorCanvas = umlEditorCanvas;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        switch (umlEditorCanvas.getMode()){
            case(UmlEditorCanvas.MODE_CREATE_CLASS):
                umlEditorCanvas.getEditorObjects().addLast(new UmlClass(e.getX(),e.getY()));
                break;
            case(UmlEditorCanvas.MODE_CREATE_USE_CASE):
                umlEditorCanvas.getEditorObjects().addLast(new UmlUseCase(e.getX(),e.getY()));
                break;
        }

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
