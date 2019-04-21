package uml;

import java.awt.*;

public class UmlGeneralizationLine extends UmlConnectionLine {
    public static final int TRIANGLE_HEIGHT = 10;
    public static final int TRIANGLE_WIDTH = 5;
    private BasicTriangle trinagle;

    public UmlGeneralizationLine(UmlComponent source, UmlComponent target){
        super(source,target);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        Point p1,p2;
        if (source.isRightTo(target)){
            p1 = source.getConnectionPort(LEFT_PORT).getCenter();
            p2 = target.getConnectionPort(RIGHT_PORT).getCenter();
            this.trinagle = new BasicTriangle(p2.x,p2.y, TRIANGLE_WIDTH, TRIANGLE_HEIGHT,BasicTriangle.LEFT);
            p2.x += TRIANGLE_HEIGHT;
        }
        else if(source.isLeftTo(target)){
            p1 = source.getConnectionPort(RIGHT_PORT).getCenter();
            p2 = target.getConnectionPort(LEFT_PORT).getCenter();
            this.trinagle = new BasicTriangle(p2.x,p2.y, TRIANGLE_WIDTH, TRIANGLE_HEIGHT,BasicTriangle.RIGHT);
            p2.x -= TRIANGLE_HEIGHT;
        }
        else if(source.isOnTopOf(target)){
            p1 = source.getConnectionPort(LOWER_PORT).getCenter();
            p2= target.getConnectionPort(UPPER_PORT).getCenter();
            this.trinagle = new BasicTriangle(p2.x,p2.y, TRIANGLE_WIDTH, TRIANGLE_HEIGHT,BasicTriangle.DOWN);
            p2.y-= TRIANGLE_HEIGHT;
        }
        else {
            p1 = source.getConnectionPort(UPPER_PORT).getCenter();
            p2 = target.getConnectionPort(LOWER_PORT).getCenter();
            this.trinagle = new BasicTriangle(p2.x,p2.y, TRIANGLE_WIDTH, TRIANGLE_HEIGHT,BasicTriangle.UP);
            p2.y+= TRIANGLE_HEIGHT;
        }
        line.moveTo(p1,p2);
        line.draw(graphics2D);
        trinagle.draw(graphics2D);
    }
}
