package uml;

import java.awt.*;

public class UmlUseCase extends UmlComponent{
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=60;
    BasicText useCaseNameBasicText;
    BasicOval useCaseOval;
    public UmlUseCase(int x,int y){
        this.minX =x;
        this.minY = y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.useCaseNameBasicText = new BasicText(this.getBound(),"Default",BasicText.LAYOUT_CENTER);
        useCaseOval = new BasicOval(minX,minY,width,height);
        setConnectionPort();
        setComponentName("Default");
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        useCaseOval.draw(graphics2D);
        useCaseNameBasicText.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
    }

    @Override
    public void moveTo(Point p) {
        super.moveTo(p);
        useCaseOval.moveTo(p);
        useCaseNameBasicText.moveTo(p);
        setConnectionPort();
    }

    public String getUseCaseNameBasicText() {
        return this.useCaseNameBasicText.getText();
    }

    public void setUseCaseNameBasicText(String useCaseNameBasicText) {
        this.useCaseNameBasicText.setText(useCaseNameBasicText);
    }

    @Override
    public void setComponentName(String componentName) {
        super.setComponentName(componentName);
        setUseCaseNameBasicText(componentName);
    }
}
