import editor.UmlEditorCanvas;
import editor.UmlEditorControl;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main{
    private static final int WINDOW_WIDTH = 1000;
    private static final int WINDOW_HEIGHT =750;
    UmlEditorControl umlEditorControl;
    UmlEditorCanvas umlEditorCanvas;
    JFrame frame;

    public Main(){
        umlEditorControl = new UmlEditorControl(150,WINDOW_HEIGHT-50);
        umlEditorCanvas = new UmlEditorCanvas(WINDOW_WIDTH-150,WINDOW_HEIGHT-50);
        umlEditorControl.setOnItemSelectedListener(umlEditorCanvas);
        frame = new JFrame("Uml Editor");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(0);
        frame.setLayout(flowLayout);
        frame.add(umlEditorControl);
        frame.add(umlEditorCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(getMenuBar(umlEditorCanvas));

    }

    private JMenuBar getMenuBar(ActionListener listener) {
        JMenuBar menuBar = new JMenuBar();
        JMenu file = new JMenu("File");
        JMenu edit = new JMenu("Edit");
        JMenuItem fileSave = new JMenuItem("Save");
        fileSave.setActionCommand("save");
        fileSave.addActionListener(listener);
        JMenuItem editGroup = new JMenuItem("Group");
        editGroup.setActionCommand("group");
        editGroup.addActionListener(listener);
        JMenuItem editUnGroup =  new JMenuItem("UnGroup");
        editUnGroup.setActionCommand("ungroup");
        editUnGroup.addActionListener(listener);
        JMenuItem editChangeName = new JMenuItem("Change Name");
        editChangeName.setActionCommand("changeName");
        editChangeName.addActionListener(listener);
        file.add(fileSave);
        edit.add(editGroup);
        edit.add(editUnGroup);
        edit.add(editChangeName);
        menuBar.add(file);
        menuBar.add(edit);
        return menuBar;
    }

    public void start(){
        frame.setVisible(true);
    }

    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }

}
