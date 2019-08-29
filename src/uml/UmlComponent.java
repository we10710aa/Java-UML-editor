package uml;

import editor.ChangeNameCanvas;
import editor.listener.OnActionSelectedListener;
import editor.listener.OnStringResultListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.LinkedList;

public class UmlComponent extends BasicComponent implements OnActionSelectedListener {
    boolean selected;
    private List<Port> connectionPortList;
    private List<Port> resizePort;


    public static final int UPPER_PORT = 0;
    public static final int LOWER_PORT = 1;
    public static final int LEFT_PORT = 2;
    public static final int RIGHT_PORT = 3;

    protected TextView componentName=new TextView(this.getBound(),"",TextView.LAYOUT_CENTER);


    public void onSelected() {
        selected = true;
    }

    public void onUnSelected() {
        selected = false;
    }

    public boolean isSelected() {
        return selected;
    }

    void drawConnectionPort(Graphics2D graphics2D) {
        for (BasicRect port : connectionPortList) {
            port.draw(graphics2D);
        }
    }

    void setConnectionPort() {
        if (connectionPortList == null) {
            connectionPortList = new ArrayList<>();
            connectionPortList.add(UPPER_PORT, new Port((minX + width / 2 - 5), minY - 5, 10, 10,this));
            connectionPortList.add(LOWER_PORT, new Port((minX + width / 2 - 5), ((minY + height) - 5), 10, 10, this));
            connectionPortList.add(LEFT_PORT, new Port(minX - 5, (minY + height / 2 - 5), 10, 10, this));
            connectionPortList.add(RIGHT_PORT, new Port((minX + width) - 5, (minY + height / 2 - 5), 10, 10, this));
        }else{
            connectionPortList.get(UPPER_PORT).moveTo(new Point((minX + width / 2 - 5), minY - 5));
            connectionPortList.get(LOWER_PORT).moveTo(new Point((minX + width / 2 - 5), ((minY + height) - 5)));
            connectionPortList.get(LEFT_PORT).moveTo(new Point(minX - 5, (minY + height / 2 - 5)));
            connectionPortList.get(RIGHT_PORT).moveTo(new Point((minX + width) - 5, (minY + height / 2 - 5)));
        }
    }

    public Port getConnectionPort(int port) {
        return connectionPortList.get(port);
    }

    public boolean isRightTo(UmlComponent component) {
        return (this.minX > component.minX + component.width);
    }

    public boolean isLeftTo(UmlComponent component) {
        return (this.minX + this.width < component.minX);
    }

    public boolean isBelow(UmlComponent component) {
        return (this.minY > component.minY + component.height);
    }

    public boolean isOnTopOf(UmlComponent component) {
        return (this.minY + this.height < component.minY);
    }

    public UmlComponent getUmlComponent(Point p) {
        return this;
    }

    public void changeComponentName() {
        ChangeNameCanvas changeNameCanvas = new ChangeNameCanvas(this.getComponentName());
        changeNameCanvas.setOnStringResultListener(new OnStringResultListener() {
            @Override
            public void onStringResult(String result) {
                UmlComponent.this.componentName.setText(result);
            }
        });
    }

    public String getComponentName() {
        return this.componentName.getText();
    }


    public LinkedList<UmlComponent> getChildComponent(){
        return null;
    }

    @Override
    public void onActionSelected(ActionEvent e) {

    }
}
