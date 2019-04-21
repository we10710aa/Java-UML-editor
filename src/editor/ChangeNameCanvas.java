package editor;

import editor.listener.OnStringResultListener;
import uml.BasicButton;
import uml.BasicText;
import uml.BasicTextField;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ChangeNameCanvas extends Canvas implements MouseListener, KeyListener {
    private OnStringResultListener onStringResultListener;
    BasicButton okButton;
    BasicButton cancelButton;
    BasicTextField textField;
    JFrame jFrame;
    public ChangeNameCanvas(String originalString){
        jFrame = new JFrame("change name");
        jFrame.setSize(600,150);
        this.setSize(600,150);
        textField = new BasicTextField(0,0,400,130,originalString,BasicText.LAYOUT_CENTER_LEFT);
        textField.setTextFont(new Font("textfont",Font.BOLD,30));
        okButton = new BasicButton(420,50,"Ok",R.id.change_name_ok);
        cancelButton = new BasicButton(520,50,"Cancel",R.id.change_name_cancel);
        this.addMouseListener(this);
        this.addKeyListener(this);
        jFrame.add(this);
        jFrame.setVisible(true);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D) g;
        textField.draw(graphics2D);
        okButton.draw(graphics2D);
        cancelButton.draw(graphics2D);
    }

    public void setOnStringResultListener(OnStringResultListener listener){
        this.onStringResultListener = listener;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        if(textField.contains(e.getPoint())){
            textField.setCurrentEditingPosition(e.getX(),(Graphics2D)getGraphics());
            repaint();
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if(okButton.contains(e.getPoint())){
            okButton.setState(BasicButton.STATE_SELECTED);
            onStringResultListener.onStringResult(textField.getText());

        }
        else if(cancelButton.contains(e.getPoint())){
            cancelButton.setState(BasicButton.STATE_SELECTED);

        }
        repaint();

    }

    @Override
    public void mouseReleased(MouseEvent e) {
        if(okButton.contains(e.getPoint())){
            okButton.setState(BasicButton.STATE_UNSELECTED);
            repaint();
            jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        }
        else if(cancelButton.contains(e.getPoint())){
            cancelButton.setState(BasicButton.STATE_UNSELECTED);
            repaint();
            jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        }


    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void keyTyped(KeyEvent e) {
        if(e.getExtendedKeyCode()==8){
            textField.deleteCurrentPosition();
        }
        else{
            System.out.println(e.getExtendedKeyCode());
            textField.addToCurrentPosition(e.getKeyChar());
        }
        repaint();
    }

    @Override
    public void keyPressed(KeyEvent e) {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }
}
