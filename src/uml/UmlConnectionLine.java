package uml;

import java.awt.*;

public class UmlConnectionLine extends  UmlComponent {
    private UmlComponent source;
    private UmlComponent target;

    private BasicLine line;

    public UmlConnectionLine(UmlComponent source,UmlComponent target){
        this.source = source;
        this.target = target;
        setLine();
    }

    private void setLine() {
        Point p1,p2;
        if (source.isRightTo(target)){
            p1 = source.getConnectionPort(LEFT_PORT).getCenter();
            p2 = target.getConnectionPort(RIGHT_PORT).getCenter();
        }
        else if(source.isLeftTo(target)){
            p1 = source.getConnectionPort(RIGHT_PORT).getCenter();
            p2 = target.getConnectionPort(LEFT_PORT).getCenter();
        }
        else if(source.isOnTopOf(target)){
            p1 = source.getConnectionPort(LOWER_PORT).getCenter();
            p2= target.getConnectionPort(UPPER_PORT).getCenter();
        }
        else {
            p1 = source.getConnectionPort(UPPER_PORT).getCenter();
            p2 = target.getConnectionPort(LOWER_PORT).getCenter();
        }
        if(line == null){
            line = new BasicLine(p1,p2);
        }
        else line.moveTo(p1,p2);
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        setLine();
        line.draw(graphics2D);

    }
    @Override
    public boolean contains(Point p) {
        return false;
    }

    @Override
    public boolean withIn(BasicRect rect) {
        return false;
    }

}
