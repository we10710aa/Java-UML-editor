package uml;

import java.awt.*;
import java.util.List;

public class UmlClass extends UmlComponent {
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=150;
    private List<String> attributes;
    private List<String> methods;
    private String className;

    private BasicRect classNameShape;
    private BasicRect classContentShape;
    public UmlClass(int x,int y){
            this.minX =x;
            this.minY = y;
            this.width = DEFAULT_WIDTH;
            this.height = DEFAULT_HEIGHT;
            classNameShape = new BasicRect(x,y,DEFAULT_WIDTH,30);
            classContentShape = new BasicRect(x,y+30,DEFAULT_WIDTH,DEFAULT_HEIGHT-30);
            setConnectionPort();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        classNameShape.draw(graphics2D);
        classContentShape.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
    }

    @Override
    public void onSelected() {
        this.selected=true;
    }

    public void addAttribute(){ }

    @Override
    public void moveTo(Point p) {
        super.moveTo(p);
        classNameShape.moveTo(p);
        classContentShape.moveTo(new Point(p.x,p.y+30));
        setConnectionPort();
    }
    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }
}
