package editor;

import editor.listener.OnItemSelectedListener;
import uml.BasicButton;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class UmlEditorControl extends JPanel implements MouseListener{
    List<BasicButton> controlButtons;
    OnItemSelectedListener listener = null;
    BasicButton selectedButon;
    private int preferredWidth;
    private int preferredHeight;

    public UmlEditorControl(int width,int height){
        this.setBackground(Color.WHITE);
        this.preferredWidth = width;
        this.preferredHeight = height;
        this.addMouseListener(this);
        controlButtons = new ArrayList<>();
        controlButtons.add(new BasicButton(30,10,"Select", R.id.select));
        controlButtons.add(new BasicButton(30,80,"Association line", R.id.assoication_line));
        controlButtons.add(new BasicButton(30,150,"Generalization", R.id.generalization_line));
        controlButtons.add(new BasicButton(30,220,"Composition line", R.id.composition_line));
        controlButtons.add(new BasicButton(30,290,"Create class", R.id.create_class));
        controlButtons.add(new BasicButton(30,360,"Create use case", R.id.create_use_case));

        controlButtons.get(0).setState(BasicButton.STATE_SELECTED);
        selectedButon = controlButtons.get(0);
    }

    @Override
    public Dimension getPreferredSize() {
        return new Dimension(preferredWidth, preferredHeight);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D graphics2D = (Graphics2D)g;
        g.drawLine(getWidth(),0,getWidth(),getHeight());
        for(BasicButton button:controlButtons){
            button.draw(graphics2D);
        }
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        for (BasicButton button:controlButtons){
            if(button.contains(e.getPoint())){
                button.setState(BasicButton.STATE_SELECTED);
                selectedButon = button;
            }
        }
        for (BasicButton button:controlButtons){
            if(!button.equals(selectedButon)){
                button.setState(BasicButton.STATE_UNSELECTED);
        }
        }
        if(listener!=null){
            listener.onItemSelected(selectedButon.getId());
        }
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public void setOnItemSelectedListener(OnItemSelectedListener onItemSelectedListener){
        this.listener = onItemSelectedListener;
    }
}
