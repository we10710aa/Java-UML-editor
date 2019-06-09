package uml;

import java.awt.*;

public class UmlUseCase extends UmlComponent{
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=60;
    TextView useCaseNameTextView;
    BasicOval useCaseOval;
    public UmlUseCase(int x,int y){
        this.minX =x;
        this.minY = y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.useCaseNameTextView = new TextView(this.getBound(),"Default", TextView.LAYOUT_CENTER);
        useCaseOval = new BasicOval(minX,minY,width,height,BasicOval.SOLID);
        useCaseOval.setFillColor(Color.gray);
        setConnectionPort();
        setComponentName("Default");
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        useCaseOval.draw(graphics2D);
        useCaseNameTextView.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
    }

    @Override
    public void moveTo(Point p) {
        super.moveTo(p);
        useCaseOval.moveTo(p);
        useCaseNameTextView.moveTo(p);
        setConnectionPort();
    }

    @Override
    public String getComponentName() {
        return this.useCaseNameTextView.getText();
    }

    @Override
    public void setComponentName(String componentName) {
        this.useCaseNameTextView.setText(componentName);
    }
}
