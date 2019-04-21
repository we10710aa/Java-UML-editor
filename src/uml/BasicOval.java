package uml;

import java.awt.*;

public class BasicOval extends BasicComponent {
    public static final int HOLLOW =0;
    public static final int SOLID=1;
    private int fillType=0;
    private Color fillColor = Color.BLACK;


    public BasicOval(int x,int y,int width,int height){
        super(x, y, width, height);
    }

    public BasicOval(int x,int y,int width,int height,int fillType){
        this(x,y,width,height);
        this.fillType = fillType;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(fillType == HOLLOW) {
            graphics2D.drawOval(minX, minY, width, height);
        }
        else if(fillType == SOLID){
            graphics2D.setColor(fillColor);
            graphics2D.fillOval(minX,minY,width,height);
            graphics2D.setColor(Color.BLACK);
            graphics2D.drawOval(minX,minY,width,height);
        }
    }

    public void setFillColor(Color color){
        this.fillColor = color;
    }
}
