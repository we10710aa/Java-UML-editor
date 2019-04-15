package uml;

import java.awt.*;

public class UmlClass extends UmlComponent {
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=150;
    public UmlClass(int x,int y){
            this.minX =x;
            this.minY = y;
            this.width = DEFAULT_WIDTH;
            this.height = DEFAULT_HEIGHT;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawRect(minX,minY,width,height);
    }
}
