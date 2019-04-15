import editor.UmlEditorCanvas;

import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class Main {
    private Frame mainFrame;
    Main(){
        mainFrame = new Frame("editor.UmlEditorCanvas");
        mainFrame.setSize(600,600);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });


        mainFrame.add(new UmlEditorCanvas());
        mainFrame.setVisible(true);
    }

    public static void main(String[] args){
        Main main = new Main();
    }
}
