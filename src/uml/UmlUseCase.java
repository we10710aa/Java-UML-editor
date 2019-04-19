package uml;

import java.awt.*;

public class UmlUseCase extends UmlComponent{
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=60;
    BasicText useCaseName;
    BasicOval useCaseOval;
    public UmlUseCase(int x,int y){
        this.minX =x;
        this.minY = y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;
        this.useCaseName = new BasicText(this.getBound(),"Default case",BasicText.LAYOUT_CENTER);
        useCaseOval = new BasicOval(minX,minY,width,height);
        setConnectionPort();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        useCaseOval.draw(graphics2D);
        useCaseName.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
    }

    @Override
    public void moveTo(Point p) {
        super.moveTo(p);
        useCaseOval.moveTo(p);
        useCaseName.moveTo(p);
        setConnectionPort();
    }

    public String getUseCaseName() {
        return this.useCaseName.getText();
    }

    public void setUseCaseName(String useCaseName) {
        this.useCaseName.setText(useCaseName);
    }


}
