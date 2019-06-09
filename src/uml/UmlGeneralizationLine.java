package uml;

import java.awt.*;

public class UmlGeneralizationLine extends UmlConnectionLine {
    public static final int TRIANGLE_HEIGHT = 10;
    public static final int TRIANGLE_WIDTH = 5;

    public UmlGeneralizationLine(UmlComponent source, UmlComponent target){
        super(source,target);
        this.arrowSize=10;
    }

    @Override
    protected void drawArrow(Graphics2D graphics2D, Point target, int direction) {
        switch (direction){
            case UP:
                graphics2D.drawPolygon(
                        new int[]{target.x,target.x+arrowSize/2,target.x-arrowSize/2,target.x},
                        new int[]{target.y,target.y+arrowSize,target.y+arrowSize,target.y}, 4);
                break;
            case DOWN:
                graphics2D.drawPolygon(
                        new int[]{target.x,target.x+arrowSize/2,target.x-arrowSize/2,target.x},
                        new int[]{target.y,target.y-arrowSize,target.y-arrowSize,target.y}, 4);
                break;
            case LEFT:
                graphics2D.drawPolygon(
                        new int[]{target.x,target.x+arrowSize,target.x+arrowSize,target.x},
                        new int[]{target.y,target.y-arrowSize/2,target.y+arrowSize/2,target.y}, 4);
                break;
            case RIGHT:
                graphics2D.drawPolygon(
                        new int[]{target.x,target.x-arrowSize,target.x-arrowSize,target.x},
                        new int[]{target.y,target.y-arrowSize/2,target.y+arrowSize/2,target.y}, 4);
                break;
        }
    }
}
