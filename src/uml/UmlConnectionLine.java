package uml;

import editor.R;

import java.awt.*;
import java.awt.geom.GeneralPath;

public class UmlConnectionLine extends BasicComponent {
    public static final int UP = 0;
    public static final int DOWN = 1;
    public static final int LEFT = 2;
    public static final int RIGHT = 3;

    UmlComponent source;
    UmlComponent target;
    int arrowSize = 0;

    public UmlConnectionLine(UmlComponent source, UmlComponent target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Point p1, p2;
        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        if(source.isRightTo(target)){
            p2 = target.getConnectionPort(UmlComponent.RIGHT_PORT).getCenter();
            drawArrow(graphics2D,p2,LEFT);
            path.moveTo(p2.getX()+arrowSize,p2.getY());
            if(source.isOnTopOf(target)){
                p1= source.getConnectionPort(UmlComponent.LOWER_PORT).getCenter();
                path.lineTo(p1.getX(),p2.getY());
                path.lineTo(p1.getX(),p1.getY());
            }
            else if(source.isBelow(target)){
                p1 =source.getConnectionPort(UmlComponent.UPPER_PORT).getCenter();
                path.lineTo(p1.getX(),p2.getY());
                path.lineTo(p1.getX(),p1.getY());
            }
            else {
                p1 = source.getConnectionPort(UmlComponent.LEFT_PORT).getCenter();
                path.lineTo((p1.getX()+p2.getX())/2,p2.getY());
                path.lineTo((p1.getX()+p2.getX())/2,p1.getY());
                path.lineTo(p1.getX(),p1.getY());
            }
        }
        else if(source.isLeftTo(target)){
            p2 = target.getConnectionPort(UmlComponent.LEFT_PORT).getCenter();
            drawArrow(graphics2D,p2,RIGHT);
            path.moveTo(p2.getX()-arrowSize,p2.getY());
            if(source.isOnTopOf(target)){
                p1= source.getConnectionPort(UmlComponent.LOWER_PORT).getCenter();
                path.lineTo(p1.getX(),p2.getY());
                path.lineTo(p1.getX(),p1.getY());
            }
            else if(source.isBelow(target)){
                p1 =source.getConnectionPort(UmlComponent.UPPER_PORT).getCenter();
                path.lineTo(p1.getX(),p2.getY());
                path.lineTo(p1.getX(),p1.getY());
            }
            else {
                p1 = source.getConnectionPort(UmlComponent.RIGHT_PORT).getCenter();
                path.lineTo((p1.getX()+p2.getX())/2,p2.getY());
                path.lineTo((p1.getX()+p2.getX())/2,p1.getY());
                path.lineTo(p1.getX(),p1.getY());
            }
        }
        else if(source.isOnTopOf(target)){
            p1 = source.getConnectionPort(UmlComponent.LOWER_PORT).getCenter();
            p2 = target.getConnectionPort(UmlComponent.UPPER_PORT).getCenter();
            drawArrow(graphics2D,p2,DOWN);

            path.moveTo(p2.getX(),p2.getY()-arrowSize);
            path.lineTo(p2.getX(),(p1.getY()+p2.getY())/2);
            path.lineTo(p1.getX(),(p1.getY()+p2.getY())/2);
            path.lineTo(p1.getX(),p1.getY());
        }
        else if(source.isBelow(target)){
            p1= source.getConnectionPort(UmlComponent.UPPER_PORT).getCenter();
            p2 = target.getConnectionPort(UmlComponent.LOWER_PORT).getCenter();
            drawArrow(graphics2D,p2,UP);
            path.moveTo(p2.getX(),p2.getY()+arrowSize);
            path.lineTo(p2.getX(),(p1.getY()+p2.getY())/2);
            path.lineTo(p1.getX(),(p1.getY()+p2.getY())/2);
            path.lineTo(p1.getX(),p1.getY());
        }
        graphics2D.draw(path);
    }

    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public boolean withIn(BasicRect rect) {
        return false;
    }

    protected void drawArrow(Graphics2D graphics2D, Point target, int direction) {
    }


}
