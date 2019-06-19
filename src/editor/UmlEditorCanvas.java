package editor;

import editor.listener.*;
import uml.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.LinkedList;


public class UmlEditorCanvas extends JPanel implements ActionListener {
    public static final int MODE_SELECT = 1;
    public static final int MODE_ASSOCIATION_LINE=2;
    public static final int MODE_GENERALIZATION_LINE = 3;
    public static final int MODE_COMPOSITION_LINE = 4;
    public static final int MODE_CREATE_CLASS = 5;
    public static final int MODE_CREATE_USE_CASE = 6;

    private MouseAdapter selectAdapter;
    private MouseAdapter createComponentAdapter;
    private MouseAdapter createLineAdapter;

    private int currentMode;


    private int preferredWidth;
    private int preferredHeight;

    private LinkedList<UmlComponent> editorObjects;
    private LinkedList<UmlConnectionLine> connectionLines;

    public UmlEditorCanvas(int width, int height) {
        connectionLines = new LinkedList<>();
        editorObjects = new LinkedList<>();
        this.setBackground(Color.WHITE);
        preferredWidth = width;
        preferredHeight = height;
        selectAdapter = new SelectModeListener(this);
        createComponentAdapter = new CreateComponentListener(this);
        createLineAdapter = new CreateLineListener(this);
        this.addMouseListener(selectAdapter);
        this.addMouseMotionListener(selectAdapter);
        this.currentMode=1;
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (UmlComponent component : editorObjects) {
            component.draw(g2);
        }
        for (UmlConnectionLine connectionLine : connectionLines) {
            connectionLine.draw(g2);
        }
    }

    public LinkedList<UmlComponent> getEditorObjects() {
        return editorObjects;
    }


    public void setMode(int mode) {
        this.currentMode = mode;
        this.removeAllMouseListener();
        switch (mode){
            case MODE_SELECT:
                this.addMouseListener(selectAdapter);
                this.addMouseMotionListener(selectAdapter);
                break;
            case MODE_ASSOCIATION_LINE:
            case MODE_COMPOSITION_LINE:
            case MODE_GENERALIZATION_LINE:
                this.addMouseListener(createLineAdapter);
                this.addMouseMotionListener(createLineAdapter);
                break;
            case MODE_CREATE_CLASS:
            case MODE_CREATE_USE_CASE:
                this.addMouseListener(createComponentAdapter);
                this.addMouseMotionListener(createComponentAdapter);
                break;
        }
    }

    private void removeAllMouseListener(){
        for(MouseListener listener:this.getMouseListeners()){
            this.removeMouseListener(listener);
        }
        for(MouseMotionListener listener:this.getMouseMotionListeners()){
            this.removeMouseMotionListener(listener);
        }
    }

    public int getMode(){ return this.currentMode; }

    public LinkedList<UmlConnectionLine> getConnectionLines() {
        return connectionLines;
    }

    private void changeComponentName() {
        int selectedComponentAmount = 0;
        UmlComponent selectedComponent = null;
        for (UmlComponent component : editorObjects) {
            if (component.isSelected()) {
                selectedComponent = component;
                selectedComponentAmount += 1;
            }
        }
        if (selectedComponentAmount == 1) {
            selectedComponent.changeComponentName();
        }
    }

    private void groupToComposition() {
        LinkedList<UmlComponent> selected = new LinkedList<>();
        for (int i = 0; i < editorObjects.size(); i++) {
            if (editorObjects.get(i).isSelected()) {
                selected.addLast(editorObjects.get(i));
            }
        }
        if (!selected.isEmpty()) {
            editorObjects.addLast(new UmlComposition(selected));
            for (UmlComponent component : selected) {
                component.onUnSelected();
                editorObjects.removeFirstOccurrence(component);
            }
        }
        editorObjects.getLast().onSelected();
    }

    private void ungrouptoComponent() {
        int selectedComponentAmount = 0;
        UmlComponent selectedComponent = null;
        for (UmlComponent component : editorObjects) {
            if (component.isSelected()) {
                selectedComponent = component;
                selectedComponentAmount += 1;
            }
        }
        if (selectedComponentAmount == 1) {
            LinkedList<UmlComponent> components = selectedComponent.getChildComponent();
            if (components != null) {
                editorObjects.removeFirstOccurrence(selectedComponent);
                for (UmlComponent component : components) {
                    editorObjects.addLast(component);
                    component.onSelected();
                }
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (getMode()!=MODE_SELECT) {
            return;
        }
        if (e.getActionCommand().equals("group")) {
            groupToComposition();
        } else if (e.getActionCommand().equals("ungroup")) {
            ungrouptoComponent();
        } else if (e.getActionCommand().equals("changeName")) {
            System.out.println("hello");
            changeComponentName();
        }
    }
}
