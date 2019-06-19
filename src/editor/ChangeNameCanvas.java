package editor;

import editor.listener.OnStringResultListener;
import uml.BasicButton;
import uml.TextView;
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
    private static final int OK =0;
    private static final int CANCLE=1;
    public ChangeNameCanvas(String originalString){
        jFrame = new JFrame("change name");
        jFrame.setSize(600,150);
        this.setSize(600,150);
        textField = new BasicTextField(0,0,400,130,originalString, TextView.LAYOUT_CENTER_LEFT);
        textField.setTextFont(new Font("textfont",Font.BOLD,30));
        okButton = new BasicButton(420,50,"Ok",OK);
        cancelButton = new BasicButton(520,50,"Cancel",CANCLE);
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
            jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        }
        else if(cancelButton.contains(e.getPoint())){
            cancelButton.setState(BasicButton.STATE_UNSELECTED);
            jFrame.dispatchEvent(new WindowEvent(jFrame, WindowEvent.WINDOW_CLOSING));
        }
        repaint();
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
        else if(e.getKeyChar() == KeyEvent.VK_BACK_SPACE){
            textField.deleteCurrentPosition();
        }
        else{
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
