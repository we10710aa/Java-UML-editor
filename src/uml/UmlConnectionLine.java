package uml;

import java.awt.*;

public class UmlConnectionLine extends  UmlComponent {
    private UmlComponent source;
    private UmlComponent traget;

    public UmlConnectionLine(UmlComponent source,UmlComponent target){
        this.source = source;
        this.traget = target;

    }

    @Override
    public void draw(Graphics2D graphics2D) {

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
