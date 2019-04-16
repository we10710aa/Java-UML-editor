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
        if(selected){
            graphics2D.fillRect(minX-5,(minY+height/2-5),10,10);
            graphics2D.fillRect((minX+width)-5,(minY+height/2-5),10,10);
            graphics2D.fillRect((minX+width/2-5),minY-5,10,10);
            graphics2D.fillRect((minX+width/2-5),((minY+height)-5),10,10);

        }
    }

    @Override
    public void onSelected() {
        this.selected=true;
    }
}
