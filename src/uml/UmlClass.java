package uml;

import editor.ChangeNameCanvas;
import editor.listener.OnStringResultListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

public class UmlClass extends UmlComponent {
    private static final int DEFAULT_WIDTH=100;
    private static final int DEFAULT_HEIGHT=150;
    private List<String> attributes;
    private List<String> operations;
    private BasicRect classRect;
    private TextView attributesTextView;
    private TextView methodsTextView;

    public UmlClass(int x, int y) {
        this.minX = x;
        this.minY = y;
        this.width = DEFAULT_WIDTH;
        this.height = DEFAULT_HEIGHT;

        attributes = new ArrayList<>();
        operations = new ArrayList<>();
        attributes.add("helllo");
        this.classRect = new BasicRect(x,y,DEFAULT_WIDTH,DEFAULT_HEIGHT,BasicRect.SOLID);
        classRect.setFillColor(Color.gray);

        this.componentName = new TextView(x,y,DEFAULT_WIDTH,30, "Default class", TextView.LAYOUT_CENTER);
        componentName.setDrawBound(true);
        attributesTextView = new TextView(x,y+30,DEFAULT_WIDTH,0,"",TextView.LAYOUT_TOP_LEFT);
        attributesTextView.setDrawBound(true);
        setConnectionPort();
    }

    @Override
    public void draw(Graphics2D graphics2D) {
        classRect.draw(graphics2D);
        if(selected){
            drawConnectionPort(graphics2D);
        }
        componentName.draw(graphics2D);
        attributesTextView.setText(String.join("\n",attributes));
        attributesTextView.draw(graphics2D);
    }

    @Override
    public void moveTo(Point p) {
        super.moveTo(p);
        componentName.moveTo(p);
        attributesTextView.moveTo(new Point(p.x,p.y+componentName.height));
        classRect.moveTo(p);
        setConnectionPort();
    }

    @Override
    public void onActionSelected(ActionEvent e) {
        if(e.getActionCommand().equals("addAttribute")){
            ChangeNameCanvas changeNameCanvas = new ChangeNameCanvas("");
            changeNameCanvas.setOnStringResultListener(new OnStringResultListener() {
                @Override
                public void onStringResult(String result) {
                    UmlClass.this.attributes.add(result);
                }
            });
        }else if(e.getActionCommand().equals("removeAttribute")){
            this.attributes.remove(this.attributes.size()-1);
        }
    }
}
