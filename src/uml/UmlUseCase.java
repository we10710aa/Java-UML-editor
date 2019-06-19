package uml;

import java.awt.*;

public class UmlUseCase extends UmlComponent{
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=60;
    BasicOval useCaseOval;
    public UmlUseCase(int x,int y){
        this.minX =x;
        this.minY = y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.componentName= new TextView(this.getBound(),"Default", TextView.LAYOUT_CENTER);
        useCaseOval = new BasicOval(minX,minY,width,height,BasicOval.SOLID);
        useCaseOval.setFillColor(Color.gray);
        setConnectionPort();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        useCaseOval.draw(graphics2D);
        componentName.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
    }

    @Override
    public void moveTo(Point p) {
        super.moveTo(p);
        useCaseOval.moveTo(p);
        componentName.moveTo(p);
        setConnectionPort();
    }
}
