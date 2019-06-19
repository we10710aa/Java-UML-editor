package editor;

import editor.listener.OnItemSelectedListener;
import uml.BasicButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class UmlEditorControl extends JPanel implements MouseListener {
    List<BasicButton> controlButtons;
    BasicButton selectedButon;
    private int preferredWidth;
    private int preferredHeight;
    private UmlEditorCanvas editorCanvas;

    public UmlEditorControl(int width, int height, UmlEditorCanvas canvas) {
        this.setBackground(Color.WHITE);
        this.preferredWidth = width;
        this.preferredHeight = height;
        this.editorCanvas = canvas;
        this.addMouseListener(this);
        controlButtons = new ArrayList<>();
        controlButtons.add(new BasicButton(30, 10, "Select", UmlEditorCanvas.MODE_SELECT));
        controlButtons.add(new BasicButton(30, 80, "Association line", UmlEditorCanvas.MODE_ASSOCIATION_LINE));
        controlButtons.add(new BasicButton(30, 150, "Generalization", UmlEditorCanvas.MODE_GENERALIZATION_LINE));
        controlButtons.add(new BasicButton(30, 220, "Composition line", UmlEditorCanvas.MODE_COMPOSITION_LINE));
        controlButtons.add(new BasicButton(30, 290, "Create class", UmlEditorCanvas.MODE_CREATE_CLASS));
        controlButtons.add(new BasicButton(30, 360, "Create use case", UmlEditorCanvas.MODE_CREATE_USE_CASE));

        controlButtons.get(0).setState(BasicButton.STATE_SELECTED);
        selectedButon = controlButtons.get(0);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D) g;
        g.drawLine(getWidth(), 0, getWidth(), getHeight());
        for (BasicButton button : controlButtons) {
            button.draw(graphics2D);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (BasicButton button : controlButtons) {
            if (button.contains(e.getPoint())) {
                button.setState(BasicButton.STATE_SELECTED);
                selectedButon = button;
            }
        }
        for (BasicButton button : controlButtons) {
            if (!button.equals(selectedButon)) {
                button.setState(BasicButton.STATE_UNSELECTED);
            }
        }
        editorCanvas.setMode(selectedButon.getId());
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
