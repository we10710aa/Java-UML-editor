package uml;

import java.awt.*;
import java.util.List;
public class TextView extends BasicComponent {
    private String text;
    private Font textFont = new Font("text",Font.BOLD,14);

    private int layoutMode;
    public static final int LAYOUT_TOP_LEFT=0;
    public static final int LAYOUT_CENTER=1;
    public static final int LAYOUT_TOPC_ENTER=2;
    public static final int LAYOUT_BOTTOM_LEFT=3;
    public static final int LAYOUT_CENTER_LEFT=4;



    private boolean drawBound = false;

    public TextView(int x, int y, int width, int height, String text, int layoutMode){
        this.minX = x;
        this.minY = y;
        this.width = width;
        this.height = height;
        this.text = text;
        this.layoutMode = layoutMode;
    }
    public TextView(BasicRect parent, String text, int layoutMode){
        this(parent.minX,parent.minY,parent.width,parent.height,text,layoutMode);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.setFont(textFont);
        Point layout = getLayoutPoint(graphics2D);
        for(String line :this.text.split("\n")){
            graphics2D.drawString(line,layout.x,layout.y);
            layout.y+=graphics2D.getFontMetrics().getHeight();
        }
        if(drawBound){
            graphics2D.drawRect(this.minX,this.minY,this.width,this.height);
        }
    }

    Point getLayoutPoint(Graphics2D graphics2D){
        FontMetrics metric = graphics2D.getFontMetrics();
        String[] list = this.text.split("\n");
        if(list.length*(metric.getHeight())>height){
            this.height =list.length*(metric.getHeight());
        }

        int layoutX=minX,layoutY=minY;
        switch (layoutMode){
            case LAYOUT_CENTER:
                layoutX = minX+(width-metric.stringWidth(text))/2;
                layoutY = minY +(height - metric.getHeight()*list.length)/2+metric.getAscent();
                break;
            case LAYOUT_TOP_LEFT:
                layoutX = minX + metric.getDescent();
                layoutY = minY + metric.getAscent();
                break;
            case LAYOUT_TOPC_ENTER:
                layoutX = minX+(width-metric.stringWidth(text))/2;
                layoutY = minY + metric.getAscent();
                break;
            case LAYOUT_BOTTOM_LEFT:
                layoutX = minX + metric.getDescent();
                layoutY = minY + height - metric.getDescent();
                break;
            case LAYOUT_CENTER_LEFT:
                layoutX = minX + metric.getDescent();
                layoutY = minY +(height - metric.getHeight()*list.length)/2+metric.getAscent();
        }
        return new Point(layoutX,layoutY);
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }


    public Font getTextFont() {
        return textFont;
    }

    public void setTextFont(Font textFont) {
        this.textFont = textFont;
    }

    public int getLayoutMode() {
        return layoutMode;
    }

    public void setLayoutMode(int layoutMode) {
        this.layoutMode = layoutMode;
    }

    public void setDrawBound(boolean drawBound) {
        this.drawBound = drawBound;
    }
}
