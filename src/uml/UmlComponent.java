package uml;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UmlComponent extends BasicComponent{
    boolean selected;
    private List<BasicRect> connectionPortList;
    public static int UPPER_PORT = 0;
    public static int LOWER_PORT = 1;
    public static int LEFT_PORT = 2;
    public static int RIGHT_PORT = 3;


    public void onSelected(){
        selected = true;
    }

    public  void onUnSelected(){
        selected = false;
    }

    void drawConnectionPort(Graphics2D graphics2D){
        for(BasicRect port:connectionPortList){
            port.draw(graphics2D);
        }
    }

    void setConnectionPort(){
        if(connectionPortList==null) {
            connectionPortList = new ArrayList<>();
        }
        connectionPortList.clear();
        connectionPortList.add(UPPER_PORT,new BasicRect(minX-5,(minY+height/2-5),10,10,BasicRect.SOLID));
        connectionPortList.add(LOWER_PORT,new BasicRect((minX+width)-5,(minY+height/2-5),10,10,BasicRect.SOLID));
        connectionPortList.add(LEFT_PORT,new BasicRect((minX+width/2-5),minY-5,10,10,BasicRect.SOLID));
        connectionPortList.add(RIGHT_PORT,new BasicRect((minX+width/2-5),((minY+height)-5),10,10,BasicRect.SOLID));
    }

}
