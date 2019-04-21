package editor;

import editor.listener.*;
import uml.*;

import javax.swing.*;
import java.awt.*;
import java.util.LinkedList;


public class UmlEditorCanvas extends Canvas implements OnItemSelectedListener {
    public static final int MODE_SELECT = 1;
    public static final int MODE_ASSOCIATION_LINE=2;
    public static final int MODE_GENERALIZATION_LINE = 3;
    public static final int MODE_COMPOSITION_LINE = 4;
    public static final int MODE_CREATE_CLASS = 5;
    public static final int MODE_CREATE_USE_CASE = 6;

    private int mode = MODE_SELECT;

    private LinkedList<UmlComponent> editorObjects;
    private LinkedList<UmlConnectionLine> connectionLines;

    public UmlEditorCanvas(int width, int height){
        connectionLines = new LinkedList<>();
        editorObjects = new LinkedList<>();
        editorObjects.addLast(new UmlClass(50,50));
        editorObjects.addLast(new UmlClass(50,250));
        editorObjects.addLast(new UmlUseCase(300,50));

        editorObjects.add(new UmlCompositionLine(editorObjects.get(1),editorObjects.get(2)));
        this.setBackground(Color.WHITE);
        this.setSize(width,height);
        this.addMouseListener(new SelectModeListener(this));
        this.addMouseListener(new CreateComponentListener(this));
        this.addMouseListener(new CreateLineListener(this));

    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        for(UmlComponent component :editorObjects){
            component.draw(g2);
        }
        for(UmlConnectionLine connectionLine:connectionLines){
            connectionLine.draw(g2);
        }
    }

    public LinkedList<UmlComponent> getEditorObjects() {
        return editorObjects;
    }

    public int getMode() {
        return mode;
    }

    public void setMode(int mode) {
        this.mode = mode;
    }

    public LinkedList<UmlConnectionLine> getConnectionLines() {
        return connectionLines;
    }

    @Override
    public void onItemSelected(int itemId) {
        switch (itemId){
            case(R.id.select):
                setMode(MODE_SELECT);
                break;
            case (R.id.assoication_line):
                setMode(MODE_ASSOCIATION_LINE);
                break;
            case (R.id.generalization_line):
                setMode(MODE_GENERALIZATION_LINE);
                break;
            case(R.id.composition_line):
                setMode(MODE_COMPOSITION_LINE);
                break;
            case(R.id.create_class):
                setMode(MODE_CREATE_CLASS);
                break;
            case(R.id.create_use_case):
                setMode(MODE_CREATE_USE_CASE);
                break;
            case (R.id.group):
                groupToComposition();
                break;
            case (R.id.un_group):
                ungrouptoComponent();
                break;
            case(R.id.change_name):
                changeComponentName();
                break;
        }
    }

    private void changeComponentName() {
        int selectedComponentAmount=0;
        UmlComponent selectedComponent=null;
        for(UmlComponent component:editorObjects){
            if(component.isSelected()){
                selectedComponent = component;
                selectedComponentAmount+=1;
            }
        }
        if(selectedComponentAmount==1 && !(selectedComponent instanceof UmlComposition)){
            ChangeNameCanvas changeNameCanvas = new ChangeNameCanvas(selectedComponent.getComponentName());
            UmlComponent finalSelectedComponent = selectedComponent;
            changeNameCanvas.setOnStringResultListener(new OnStringResultListener() {
                @Override
                public void onStringResult(String result) {
                    finalSelectedComponent.setComponentName(result);
                    repaint();
                }
            });
        }
    }

    private void groupToComposition(){
        LinkedList<UmlComponent> selected = new LinkedList<>();
        for(int i = 0;i<editorObjects.size();i++){
            if(editorObjects.get(i).isSelected()){
                selected.addLast(editorObjects.get(i));
            }
        }
        if(!selected.isEmpty()){
            editorObjects.addLast(new UmlComposition(selected));
            for(UmlComponent component:selected){
                component.onUnSelected();
                editorObjects.removeFirstOccurrence(component);
            }
        }
        editorObjects.getLast().onSelected();
        repaint();
    }

    private void ungrouptoComponent(){
        int selectedComponentAmount=0;
        UmlComponent selectedComponent=null;
        for(UmlComponent component:editorObjects){
            if(component.isSelected()){
                selectedComponent = component;
                selectedComponentAmount+=1;
            }
        }
        if(selectedComponentAmount==1&&selectedComponent instanceof UmlComposition){
            LinkedList<UmlComponent> components = ((UmlComposition)selectedComponent).getChildList();
            editorObjects.removeFirstOccurrence(selectedComponent);
            for(UmlComponent component:components){
                editorObjects.addLast(component);
                component.onSelected();
            }
        }
        repaint();
    }
}
