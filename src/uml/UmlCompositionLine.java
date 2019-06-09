package uml;

import java.awt.*;

public class UmlCompositionLine extends UmlConnectionLine {
    private BasicDiamond diamond;

    public UmlCompositionLine(UmlComponent source,UmlComponent target)
    {
        super(source,target);
        this.arrowSize=10;
    }

    @Override
    protected void drawArrow(Graphics2D graphics2D, Point target, int direction) {
        switch (direction){
            case UP:
                graphics2D.drawPolygon(
                        new int[]{target.x,target.x-arrowSize/2,target.x,target.x+arrowSize/2,target.x},
                        new int[]{target.y,target.y+arrowSize/2,target.y+arrowSize,target.y+arrowSize/2,target.y},
                        5
                );
                break;
            case DOWN:
                graphics2D.drawPolygon(
                        new int[]{target.x-arrowSize/2,target.x,target.x+arrowSize/2,target.x,target.x-arrowSize/2},
                        new int[]{target.y-arrowSize/2,target.y,target.y-arrowSize/2,target.y-arrowSize,target.y-arrowSize/2},
                        5
                );
                break;
            case LEFT:
                graphics2D.drawPolygon(
                        new int[]{target.x,target.x+arrowSize/2,target.x+arrowSize,target.x+arrowSize/2,target.x},
                        new int[]{target.y,target.y+arrowSize/2,target.y,target.y-arrowSize/2,target.y},
                        5
                );
                break;
            case RIGHT:
                graphics2D.drawPolygon(
                        new int[]{target.x-arrowSize,target.x-arrowSize/2,target.x,target.x-arrowSize/2,target.x-arrowSize},
                        new int[]{target.y,target.y+arrowSize/2,target.y,target.y-arrowSize/2,target.y},
                        5
                );
                break;
        }
    }
}
