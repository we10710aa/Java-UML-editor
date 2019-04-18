package uml;

import java.awt.*;

public class BasicOval extends BasicComponent {
    public BasicOval(int x,int y,int width,int height){
        super(x, y, width, height);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawOval(minX,minY,width,height);
    }
}
