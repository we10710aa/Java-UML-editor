package uml;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UmlClass extends UmlComponent {
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=150;
    private List<BasicText> attributes;
    private List<BasicText> operations;
    private BasicText className;

    private BasicRect classNameShape;
    private BasicRect classAttributeShape;
    private BasicRect classOperationShape;
    public UmlClass(int x,int y){
            this.minX =x;
            this.minY = y;
            this.width = DEFAULT_WIDTH;
            this.height = DEFAULT_HEIGHT;
            attributes = new ArrayList<>();
            operations = new ArrayList<>();
            classNameShape = new BasicRect(x,y,DEFAULT_WIDTH,30);
            classAttributeShape = new BasicRect(x,y+30,DEFAULT_WIDTH,DEFAULT_HEIGHT-30);
            this.className = new BasicText(classNameShape.getBound(),"Default class",BasicText.LAYOUT_CENTER);
            setConnectionPort();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        classNameShape.draw(graphics2D);
        classAttributeShape.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
        className.draw(graphics2D);
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
        classAttributeShape.moveTo(new Point(p.x,p.y+30));
        className.moveTo(p);
        setConnectionPort();
    }
    public String getClassName() {
        return this.className.getText();
    }

    public void setClassName(String className) {
        this.className.setText(className);
    }
}
