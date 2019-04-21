package uml;

import java.awt.*;

public class BasicTextField extends BasicText {
    private int currentEditingPosition;
    private int editingX;
    public BasicTextField(int x, int y, int width, int height, String text, int layoutMode) {
        super(x, y, width, height, text, layoutMode);
        currentEditingPosition = text.length();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(getTextFont());
        Point layout = getLayoutPoint(graphics2D);
        graphics2D.drawLine(layout.x-5,layout.y+5,layout.x+width-5,layout.y+5);
        setEditingX(graphics2D);
        graphics2D.drawLine(editingX, layout.y,
                editingX, layout.y-graphics2D.getFontMetrics(getTextFont()).getHeight());
        graphics2D.drawString(getText(),layout.x,layout.y);

    }

    public void setEditingX(Graphics2D graphics2D) {
        FontMetrics metric = graphics2D.getFontMetrics(getTextFont());
        this.editingX = getLayoutPoint(graphics2D).x+
                metric.stringWidth(getText().substring(0,currentEditingPosition));
    }


    public void setCurrentEditingPosition(int distanceFromStart,Graphics2D graphics2D){
        FontMetrics metric = graphics2D.getFontMetrics(getTextFont());
        Point p = getLayoutPoint(graphics2D);
        if(distanceFromStart> p.x+ metric.stringWidth(getText())){
            currentEditingPosition = getText().length();
        }
        else{
            for(int i = 0;i<=getText().length();i++){
                if(distanceFromStart < metric.stringWidth(getText().substring(0,i))){
                    currentEditingPosition = i-1;
                    break;
                }
            }
        }
    }

    public void deleteCurrentPosition(){
        String x = getText();
        if(currentEditingPosition==0){return;}
        else if(currentEditingPosition==x.length()){
            setText(x.substring(0,x.length()-1));
            currentEditingPosition--;
            return;
        }
        String newResult = x.substring(0,currentEditingPosition-1) + x.substring(currentEditingPosition);
        setText(newResult);
        currentEditingPosition--;
        System.out.println(currentEditingPosition);
    }
    public void addToCurrentPosition(char c){
        String x= getText();
        setText(x.substring(0,currentEditingPosition)+c+x.substring(currentEditingPosition));
        currentEditingPosition++;
    }
}
