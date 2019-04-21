package uml;

import java.awt.*;

public class BasicDiamond extends BasicComponent {
    public static final int UP= 0;
    public static final int DOWN=1;
    public static final int LEFT = 2;
    public static final int RIGHT =3;

    private int  direction;

    public BasicDiamond(int minX,int minY,int size,int direction){
        super(minX,minY,size,size);
        this.direction =direction;
    }


    @Override
    public void draw(Graphics2D graphics2D) {
        switch (direction){
            case UP:
                graphics2D.drawPolygon(
                        new int[]{minX,minX-width/2,minX,minX+width/2,minX},
                        new int[]{minY,minY+height/2,minY+height,minY+height/2,minY},
                        5
                );
                break;
            case DOWN:
                graphics2D.drawPolygon(
                        new int[]{minX-width/2,minX,minX+width/2,minX,minX-width/2},
                        new int[]{minY-height/2,minY,minY-height/2,minY-height,minY-height/2},
                        5
                );
                break;
            case LEFT:
                graphics2D.drawPolygon(
                        new int[]{minX,minX+width/2,minX+width,minX+width/2,minX},
                        new int[]{minY,minY+height/2,minY,minY-height/2,minY},
                        5
                );
                break;
            case RIGHT:
                graphics2D.drawPolygon(
                        new int[]{minX-width,minX-width/2,minX,minX-width/2,minX-width},
                        new int[]{minY,minY+height/2,minY,minY-height/2,minY},
                        5
                );
                break;
        }
    }


}
