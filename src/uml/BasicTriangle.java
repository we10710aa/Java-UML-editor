package uml;

import java.awt.*;

public class BasicTriangle extends BasicComponent {
    public static final int UP= 0;
    public static final int DOWN=1;
    public static final int LEFT = 2;
    public static final int RIGHT =3;

    private int  direction;

    public BasicTriangle(int pointX, int pointY, int triangleWidth, int triangleHeight,int direction){
        super(pointX,pointY,triangleWidth,triangleHeight);
        this.direction = direction;
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        switch (direction){
            case UP:
                graphics2D.drawPolygon(
                        new int[]{minX,minX+width,minX-width,minX},
                        new int[]{minY,minY+height,minY+height,minY}, 4);
                break;
            case DOWN:
                graphics2D.drawPolygon(
                        new int[]{minX,minX+width,minX-width,minX},
                        new int[]{minY,minY-height,minY-height,minY}, 4);
                break;
            case LEFT:
                graphics2D.drawPolygon(
                        new int[]{minX,minX+height,minX+height,minX},
                        new int[]{minY,minY-width,minY+width,minY}, 4);
                break;
            case RIGHT:
                graphics2D.drawPolygon(
                        new int[]{minX,minX-height,minX-height,minX},
                        new int[]{minY,minY-width,minY+width,minY}, 4);
                break;
        }
    }
}
