package uml;

import java.awt.Color;
import java.util.ArrayList;
import java.util.List;

public class Port extends BasicRect {
    UmlComponent parent;

    private List<UmlConnectionLine> connectToList;
    private List<UmlConnectionLine> connectFromLIst;
    public Port(int x, int y, int width, int height,UmlComponent parent) {
        super(x, y, width, height);
        this.parent = parent;
        setFillType(SOLID);
        setFillColor(Color.BLACK);
        connectToList = new ArrayList<>();
        connectFromLIst = new ArrayList<>();

    }
    public void addConnectToLine(UmlConnectionLine line){
        if(!this.connectToList.contains(line)) {
            this.connectToList.add(line);
        }
    }

    public void addConnectFromLine(UmlConnectionLine line){
        this.connectFromLIst.add(line);
    }

    public boolean removeConnectToLine(UmlConnectionLine line){
        return this.connectToList.remove(line);
    }

    public boolean removeConnectFromeLine(UmlConnectionLine line){
        return this.connectFromLIst.remove(line);
    }

}
