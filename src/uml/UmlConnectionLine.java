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
    Port sourcePort, destinationPort;
    public UmlConnectionLine(UmlComponent source, UmlComponent target) {
        this.source = source;
        this.target = target;
    }

    @Override
    public void draw(Graphics2D graphics2D) {

        GeneralPath path = new GeneralPath(GeneralPath.WIND_EVEN_ODD);
        if(source.isRightTo(target)){
            destinationPort = target.getConnectionPort(UmlComponent.RIGHT_PORT);
            drawArrow(graphics2D,destinationPort.getCenter(),LEFT);
            path.moveTo(destinationPort.getCenter().getX()+arrowSize,destinationPort.getCenter().getY());
            if(source.isOnTopOf(target)){
                sourcePort= source.getConnectionPort(UmlComponent.LOWER_PORT);
                path.lineTo(sourcePort.getCenter().getX(),destinationPort.getCenter().getY());
                path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
            }
            else if(source.isBelow(target)){
                sourcePort =source.getConnectionPort(UmlComponent.UPPER_PORT);
                path.lineTo(sourcePort.getCenter().getX(),destinationPort.getCenter().getY());
                path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
            }
            else {
                sourcePort = source.getConnectionPort(UmlComponent.LEFT_PORT);
                path.lineTo((sourcePort.getCenter().getX()+destinationPort.getCenter().getX())/2,destinationPort.getCenter().getY());
                path.lineTo((sourcePort.getCenter().getX()+destinationPort.getCenter().getX())/2,sourcePort.getCenter().getY());
                path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
            }
        }
        else if(source.isLeftTo(target)){
            destinationPort = target.getConnectionPort(UmlComponent.LEFT_PORT);
            drawArrow(graphics2D,destinationPort.getCenter(),RIGHT);
            path.moveTo(destinationPort.getCenter().getX()-arrowSize,destinationPort.getCenter().getY());
            if(source.isOnTopOf(target)){
                sourcePort= source.getConnectionPort(UmlComponent.LOWER_PORT);
                path.lineTo(sourcePort.getCenter().getX(),destinationPort.getCenter().getY());
                path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
            }
            else if(source.isBelow(target)){
                sourcePort = source.getConnectionPort(UmlComponent.UPPER_PORT);
                path.lineTo(sourcePort.getCenter().getX(),destinationPort.getCenter().getY());
                path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY()
                );
            }
            else {
                sourcePort = source.getConnectionPort(UmlComponent.RIGHT_PORT);
                path.lineTo((sourcePort.getCenter().getX()+destinationPort.getCenter().getX())/2,destinationPort.getCenter().getY());
                path.lineTo((sourcePort.getCenter().getX()+destinationPort.getCenter().getX())/2,sourcePort.getCenter().getY());
                path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
            }
        }
        else if(source.isOnTopOf(target)){
            sourcePort = source.getConnectionPort(UmlComponent.LOWER_PORT);
            destinationPort = target.getConnectionPort(UmlComponent.UPPER_PORT);
            drawArrow(graphics2D,destinationPort.getCenter(),DOWN);

            path.moveTo(destinationPort.getCenter().getX(),destinationPort.getCenter().getY()-arrowSize);
            path.lineTo(destinationPort.getCenter().getX(),(sourcePort.getCenter().getY()+destinationPort.getCenter().getY())/2);
            path.lineTo(sourcePort.getCenter().getX(),(sourcePort.getCenter().getY()+destinationPort.getCenter().getY())/2);
            path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
        }
        else if(source.isBelow(target)){
            sourcePort= source.getConnectionPort(UmlComponent.UPPER_PORT);
            destinationPort = target.getConnectionPort(UmlComponent.LOWER_PORT);
            drawArrow(graphics2D,destinationPort.getCenter(),UP);
            path.moveTo(destinationPort.getCenter().getX(),destinationPort.getCenter().getY()+arrowSize);
            path.lineTo(destinationPort.getCenter().getX(),(sourcePort.getCenter().getY()+destinationPort.getCenter().getY())/2);
            path.lineTo(sourcePort.getCenter().getX(),(sourcePort.getCenter().getY()+destinationPort.getCenter().getY())/2);
            path.lineTo(sourcePort.getCenter().getX(),sourcePort.getCenter().getY());
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
