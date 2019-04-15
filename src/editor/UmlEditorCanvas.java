package editor;

import editor.listener.SelectModelistener;

import java.awt.*;


public class UmlEditorCanvas extends Canvas {
    public static int MODE_SELECT = 1;

    public UmlEditorCanvas(){
        setBackground(Color.WHITE);
        setSize(600,600);
        this.addMouseListener(new SelectModelistener());
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRect(0,0,100,100);

    }

    public void changeMode(int mode){
        if(mode == MODE_SELECT){
            this.getMouseListeners()[0] = new SelectModelistener();
        }
    }

}
