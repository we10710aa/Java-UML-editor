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
    private Thread repaintThread;

    public Main(){
        umlEditorControl = new UmlEditorControl(150,WINDOW_HEIGHT);
        umlEditorCanvas = new UmlEditorCanvas(WINDOW_WIDTH-180,WINDOW_HEIGHT);
        umlEditorControl.setOnItemSelectedListener(umlEditorCanvas);
        frame = new JFrame("Uml Editor");
        frame.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        FlowLayout flowLayout = new FlowLayout(FlowLayout.LEFT);
        flowLayout.setHgap(5);
        frame.setLayout(flowLayout);
        frame.add(umlEditorControl);
        frame.add(umlEditorCanvas);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setJMenuBar(getMenuBar(umlEditorCanvas));
        repaintThread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    while (true) {
                        frame.repaint();
                        Thread.sleep(1000 / 30);
                    }
                } catch (InterruptedException ie) {
                }
            }
        });

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
        repaintThread.start();
    }

    public static void main(String[] args){
        Main main = new Main();
        main.start();
    }

}
