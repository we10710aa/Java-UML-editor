package uml;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UmlComponent extends BasicComponent {
    boolean selected;
    private List<BasicRect> connectionPortList;
    public static final int UPPER_PORT = 0;
    public static final int LOWER_PORT = 1;
    public static final int LEFT_PORT = 2;
    public static final int RIGHT_PORT = 3;

    private String componentName = "";


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
        }
        connectionPortList.clear();
        connectionPortList.add(UPPER_PORT, new BasicRect((minX + width / 2 - 5), minY - 5, 10, 10, BasicRect.SOLID));
        connectionPortList.add(LOWER_PORT, new BasicRect((minX + width / 2 - 5), ((minY + height) - 5), 10, 10, BasicRect.SOLID));
        connectionPortList.add(LEFT_PORT, new BasicRect(minX - 5, (minY + height / 2 - 5), 10, 10, BasicRect.SOLID));
        connectionPortList.add(RIGHT_PORT, new BasicRect((minX + width) - 5, (minY + height / 2 - 5), 10, 10, BasicRect.SOLID));
    }

    public BasicRect getConnectionPort(int port) {
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

    public void setComponentName(String componentName) {
        this.componentName = componentName;
    }

    public String getComponentName() {
        return this.componentName;
    }
}
