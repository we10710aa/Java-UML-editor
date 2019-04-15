import uml.listener.SelectModelistener;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.List;

public class Demo extends Canvas {
    private Frame mainFrame;

    public Demo(){
        mainFrame = new Frame("Demo");
        mainFrame.setSize(600,600);
        mainFrame.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                System.exit(0);
            }
        });
        mainFrame.setLayout(new GridLayout(2,1));
        mainFrame.add(this);
        Button button = new Button();
        button.setSize(100,100);
        button.setLabel("repaint");

        mainFrame.add(this);
        mainFrame.add(button);
        setBackground(Color.WHITE);
        setSize(600,600);
        mainFrame.setVisible(true);
        this.addMouseListener(new SelectModelistener());
    }
    public static void main(String[] args){
        Demo demo = new Demo();
        System.out.println("hello world");
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D g2 = (Graphics2D)g;
        g2.drawRect(0,0,100,100);

    }

}
