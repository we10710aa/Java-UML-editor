package uml;

import java.awt.*;

public class BasicButton extends BasicComponent {
    private static final int DEFAULT_WIDTH=70;
    private static final int DEFAULT_HEIGHT=50;
    public static final int STATE_SELECTED=0;
    public static final int STATE_UNSELECTED=1;
    private int state = STATE_UNSELECTED;
    private int id;
    private boolean enabled = true;

    private TextView buttonName;
    public BasicButton(int x,int y,String name,int id){
        super(x,y,DEFAULT_WIDTH,DEFAULT_HEIGHT);
        buttonName= new TextView(getBound(),name, TextView.LAYOUT_CENTER);
        buttonName.setTextFont(new Font("button",Font.BOLD,12));
        this.id =id;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        if(state == STATE_SELECTED){
            graphics2D.fillRect(minX,minY,width,height);
            graphics2D.setColor(Color.WHITE);
            buttonName.draw(graphics2D);
            graphics2D.setColor(Color.BLACK);
        }
        else if(state == STATE_UNSELECTED){
            graphics2D.drawRect(minX,minY,width,height);
            buttonName.draw(graphics2D);
        }
    }

    @Override
    public boolean contains(Point p) {
        if(enabled==false){
            return false;
        }
        return super.contains(p);
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public int getId(){
        return this.id;
    }
}
