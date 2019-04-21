package editor;

import editor.listener.OnItemSelectedListener;
import uml.BasicButton;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class UmlEditorControl extends Canvas implements MouseListener{
    List<BasicButton> controlButtons;
    List<BasicButton> oneTimeButtons;
    OnItemSelectedListener listener = null;
    BasicButton selectedButon;

    public UmlEditorControl(int width,int height){
        this.setBackground(Color.WHITE);
        this.setSize(width,height);
        this.addMouseListener(this);
        controlButtons = new ArrayList<>();
        controlButtons.add(new BasicButton(10,10,"Select", R.id.select));
        controlButtons.add(new BasicButton(10,80,"Association line", R.id.assoication_line));
        controlButtons.add(new BasicButton(10,150,"Generalization", R.id.generalization_line));
        controlButtons.add(new BasicButton(10,220,"Composition line", R.id.composition_line));
        controlButtons.add(new BasicButton(10,290,"Create class", R.id.create_class));
        controlButtons.add(new BasicButton(10,360,"Create use case", R.id.create_use_case));

        oneTimeButtons = new ArrayList<>();
        oneTimeButtons.add(new BasicButton(10,430,"Group", R.id.group));
        oneTimeButtons.add(new BasicButton(10,500,"Ungroup", R.id.un_group));
        oneTimeButtons.add(new BasicButton(10,570,"Change Name",R.id.change_name));

        controlButtons.get(0).setState(BasicButton.STATE_SELECTED);
        selectedButon = controlButtons.get(0);
    }

    @Override
    public void paint(Graphics g) {
        Graphics2D graphics2D = (Graphics2D)g;
        g.drawLine(getWidth(),0,getWidth(),getHeight());
        for(BasicButton button:controlButtons){
            button.draw(graphics2D);
        }
        for(BasicButton button:oneTimeButtons){
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
        if(selectedButon.getId()!=R.id.select){return;}
        for(BasicButton button:oneTimeButtons){
            if(button.contains(e.getPoint())){
                button.setState(BasicButton.STATE_SELECTED);
                if(listener!=null){
                    listener.onItemSelected(button.getId());
                }
            }
        }
        repaint();
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        for(BasicButton button:oneTimeButtons){
            button.setState(BasicButton.STATE_UNSELECTED);
            repaint();
        }
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
