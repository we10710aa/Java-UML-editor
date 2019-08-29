package uml;

import java.awt.Color;

public class Port extends BasicRect {
    UmlComponent parent;

    public Port(int x, int y, int width, int height,UmlComponent parent) {
        super(x, y, width, height);
        this.parent = parent;
        setFillType(SOLID);
        setFillColor(Color.BLACK);

    }

}
