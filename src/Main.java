import editor.UmlEditorCanvas;
import editor.UmlEditorControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT =750;
    UmlEditorControl umlEditorControl;
    UmlEditorCanvas umlEditorCanvas;
    JFrame frame;

    public Main(){
        umlEditorControl = new UmlEditorControl(100,WINDOW_HEIGHT);
        umlEditorCanvas = new UmlEditorCanvas(WINDOW_WIDTH-100,WINDOW_HEIGHT);
        umlEditorControl.setOnItemSelectedListener(umlEditorCanvas);
        frame = new JFrame("Uml Editor");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(0);
        frame.setLayout(flowLayout);
        frame.add(umlEditorControl);
        frame.add(umlEditorCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public void start(){
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }
}
